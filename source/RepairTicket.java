import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepairTicket {

    private String id;
    String customerID;
    String computerID;
    private String issue;
    private String status;
    private List<String> workflow;
    // Workflow[] workflows

    public RepairTicket(String id, String customerID, String computerID, String issue, String status) {
        this.id = id;
        this.customerID = customerID;
        this.computerID = computerID;
        this.issue = issue;
        this.status = status;
    }

    public RepairTicket() {
        //todo random unique ID generator 8# check to make ure it doesnt exist
        Set<Integer> idSet = new HashSet<>(); // Set to store generated IDs
        int customerID;

        while (true) {
            // Generate random 8-digit number
            customerID = (int) (Math.random() * 90000000) + 10000000;

            if (idSet.add(customerID)) { // Check if ID already exists in set and add it to the set if it's unique
                break;
            }
        }
        this.issue = issue;
        this.status = "Open";
        this.workflow = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public String GetIssue() {
        return this.issue;
    }

    public void EditIssue(String newIssue) {
        this.issue = newIssue;
    }

    public void AddWorkflow(String workflowStep) {
        this.workflow.add(workflowStep);
    }

    public void EditWorkflow(int index, String updatedWorkflowStep) {
        this.workflow.set(index, updatedWorkflowStep);
    }

    public void DeleteWorkflow(int index) {
        this.workflow.remove(index);
    }

    public String GetStatus() {
        return this.status;
    }

    public void Save() {
        // Code to save the repair ticket to a database or file system
    }

    public void Close() {
        this.status = "Closed";
    }
}