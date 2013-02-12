// ActionScript Document
import com.movers.Event;
import mx.events.EventDispatcher;

class com.movers.control.EventBroadcaster
{
	public var dispatchEvent:Function;
	public var addEventListener:Function;
	public var removeEventListener:Function;
	
	private static var eventBroadcaster;
	
	
	public static function getInstance() 
	{
		if( eventBroadcaster == undefined ) 
		{
			eventBroadcaster = new EventBroadcaster();
		}
		return eventBroadcaster;
	}
	
	private function EventBroadcaster() 
	{
		EventDispatcher.initialize( this );
	}
	
	public function broadcastEvent( eventName:String, eventData:Object )
	{
		var event:Event = new Event();
		event.type = eventName;
		event.data = eventData;
		
		dispatchEvent( event );
	}
}
