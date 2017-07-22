package package2;

public class GameEvent {
	//enumerations can sit in a file of its own or within a class like this one.
	//Java will automatically number the enumerations instead of defining named constants.
	public enum Event{
		LOGIC_REQUITED, REMOVE_ENTITY, AND_ENTITY, NOTIFY_LOST, NOTIFY_WIN
	}
	
	private Entity entity;
	private Event event;
	
	private String message="no message";
	
	public GameEvent(Entity entity, Event e){
		this.entity = entity;
		event = e;
	}
	
	public String getMessage(){
		return message;
	}
	public void setMessage(String message){
		this.message = message;
	}

	public Entity getEntity() {
		return entity;
	}

	public Event getEvent() {
		return event;
	}
}
