import java.util.ArrayList;

public class RepairTicketHandler {

    private static final RepairTicketSQLDatabase database = new RepairTicketSQLDatabase();

    public RepairTicketHandler() {}

    public void saveRepairTicket(RepairTicket repairTicket) {
        database.saveRepairTicket(repairTicket);
    }

    public ArrayList<RepairTicket> getRepairTicketsOnComputer(String computerID) {
        return database.getRepairTicketByComputerID(computerID);
    }

    public ArrayList<RepairTicket> getRepairTicketsOnCustomer(String customerID) {
        return database.getRepairTicketByCustomerID(customerID);
    }

    public RepairTicket getRepairTicket(String repairTicketID) {
        return database.getRepairTicketByTicketID(repairTicketID);
    }
}
