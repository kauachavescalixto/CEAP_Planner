package database;

public class PreLoadConnect {

	public cadastradosDAO cadDAO;
	public DevendoDAO devDAO;
	public EmUsoDAO usoDAO;
	public ItensDAO itensDAO;
	public TimelineDAO timelineDAO;
	
	public PreLoadConnect() {
		cadDAO = new cadastradosDAO();
		devDAO = new DevendoDAO();
		usoDAO = new EmUsoDAO();
		itensDAO = new ItensDAO();
		timelineDAO = new TimelineDAO();
		
	}
	
}
