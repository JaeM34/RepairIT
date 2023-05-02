import java.util.ArrayList;

public class RepairTicketHandler {

    private static final RepairTicketSQLDatabase database = new RepairTicketSQLDatabase();

    public RepairTicketHandler() {

    }

    public ArrayList<RepairTicket> getRepairTicketOnComputer(String computerID) {
        return database.getRepairTicketByComputerID(computerID);
    }

    public ArrayList<RepairTicket> getRepairTicketOnCustomer(String customerID) {
        return database.getRepairTicketByCustomerID(customerID);
    }

    public RepairTicket getRepairTicket(String repairTicketID) {
        return database.getRepairTicketByTicketID(repairTicketID);
    }
}
