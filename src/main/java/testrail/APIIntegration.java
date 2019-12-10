package testrail;

import common.Application;
import common.Assertion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import steps.MainSteps;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class APIIntegration {
    private static final String URL = Application.getProperty("TestTrailURL");
    private static final String USER = Application.getProperty("TestTrailLogin");
    private static final String PASSWORD = Application.getProperty("TestTrailPassword");
    private static final String TYPEID = Application.getProperty("TestTrailTypeID");
    private static final String RUNID = Application.getProperty("TestTrailRunID");
    private static final String SUITEID = Application.getProperty("TestTrailSuiteID");
    private static final String PROJECTID = Application.getProperty("TestTrailProjectID");

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String runId () {
        String bambooRunId = System.getenv("bamboo_TESTRAIL_RUN_ID");
        if (bambooRunId == null){
            return RUNID;
        } else {
            return System.getenv("bamboo_TESTRAIL_RUN_ID");
        }
    }

    public static APIClient setConnection() {
        APIClient client = new APIClient(URL);
        client.setUser(USER);
        client.setPassword(PASSWORD);
        return client;
    }

    // Adds new run with tests by ID to a test plan,
    //http://docs.gurock.com/testrail-api2/reference-plans#add_plan_entry
    public static void createTestRun() throws IOException, APIException, ParseException {
        LocalDateTime now = LocalDateTime.now();

        JSONArray caseIds = getArrayOfCasesIdWithSpicificTypeID();

        JSONArray runs = new JSONArray();
        JSONObject objectRuns = new JSONObject();
        objectRuns.put("include_all", true);
        objectRuns.put("case_ids", caseIds);
        runs.add(objectRuns);

        JSONObject runData = new JSONObject();
        runData.put("name", "autotest_run " + dtf.format(now));
        runData.put("suite_id", SUITEID);
        runData.put("include_all", false);
        runData.put("case_ids", caseIds);
        JSONObject c = (JSONObject) setConnection().sendPost("add_plan_entry/" + runId(), runData);
    }

    public static Integer getSpecificTestRunByID() throws IOException, APIException {
        LocalDateTime now = LocalDateTime.now();
        JSONObject jsonObject;
        JSONArray runs = null;
        JSONObject run;
        int id = 0;

        JSONObject plan = (JSONObject) setConnection().sendGet("get_plan/" + runId());
        JSONArray jsonArray = (JSONArray) plan.get("entries");


        for (int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            if (jsonObject.get("name").equals(String.format("autotest_run %s", dtf.format(now)))) {
                runs = (JSONArray) jsonObject.get("runs");
                run = (JSONObject) runs.get(0);
                id = Integer.parseInt(run.get("id").toString());
            }
        }

        return id;
    }

    public static void updateCaseResultsForSpecificTestrun(String comment) throws IOException, APIException, ParseException {
        int status_id = 0;
        int testRunID;


        switch (MainSteps.scenarioStatus.toLowerCase()) {
            case "passed":
                status_id = 1;
                break;
            case "blocked":
                status_id = 2;
                break;
            case "failed":
                status_id = 5;
                break;
            default:
                status_id = 1;
                break;
        }

        if (getSpecificTestRunByID() == 0)
            createTestRun();

        testRunID = getSpecificTestRunByID();
        String testDuration;
        if (MainSteps.duration.equals("0m 0s")){
            testDuration = "0m 1s";
        }else testDuration = MainSteps.duration;

        Map caseData = new HashMap();
        caseData.put("status_id", status_id);
        caseData.put("version", System.getProperty("currentVersion"));
        caseData.put("elapsed", testDuration);

        if (status_id == 1) {
            if (Assertion.message != "" || Assertion.error != "") {
                caseData.put("comment", comment + '\n' + Assertion.error + '\n' + Assertion.message);
            } else {
                caseData.put("comment", comment);
            }
        }

        if (status_id == 5) {
            caseData.put("defects", "bug");
            caseData.put("comment", comment);
        }


        JSONObject c = (JSONObject) setConnection()
                .sendPost((String.format("add_result_for_case/%d/%d", testRunID, MainSteps.scenarioId)), caseData);

        updateCaseType(testRunID);
    }

    public static void updateCaseType(int runID) throws IOException, APIException {
        Map caseData = new HashMap();
        caseData.put("type_id", TYPEID);

        JSONObject c = (JSONObject) setConnection()
                .sendPost((String.format("update_case/%d", MainSteps.scenarioId)), caseData);
    }


    // Get JSON with all test cases, return Array with IDS of tests with custom TypeID
    //http://docs.gurock.com/testrail-api2/reference-cases#get_cases
    public static JSONArray getArrayOfCasesIdWithSpicificTypeID() throws IOException, APIException, ParseException {
        JSONArray plan = (JSONArray) setConnection().sendGet("get_cases/" + PROJECTID + "&type_id=" + TYPEID);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(plan.toString());
        JSONArray json = (JSONArray) obj;

        JSONArray caseIds = new JSONArray();

        Iterator idsItr = json.iterator();
        while (idsItr.hasNext()) {
            JSONObject id = (org.json.simple.JSONObject) idsItr.next();
            caseIds.add(id.get("id"));

        }
        return caseIds;
    }
}
