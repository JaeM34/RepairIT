public class RepairTicket {
    public class RepairTicket {
        private String issue;
        private String status;
        private List<String> workflow;
        int customerID;
<<<<<<< HEAD
                Customer customer;
        Computer computer;
=======
        Customer customer;

>>>>>>> origin/main
        // Workflow[] workflows
        String status;

        public RepairTicket() {
            //todo random unique ID generator 8# check to make ure it doesnt exist
            Set<Integer> idSet = new HashSet<>(); // Set to store generated IDs
            Random rand = new Random();
            int customerID;

            do {
                id = rand.nextInt(90000000) + 10000000; // Generate random 8-digit number
            } while (!idSet.add(customerID));
            this.issue = issue;
            this.status = "Open";
            this.workflow = new ArrayList<>();
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


}