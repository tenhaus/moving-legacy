// ActionScript Document
import com.movers.control.*;
import com.movers.commands.*;
import mx.events.*;

class com.movers.control.Controller
{
   public function BankingController()
	{
		commands = new Array();
		initializeCommands();					
	}

//------------------------------------------------------------------------------
	
	private function initializeCommands()
	{
		addCommand( "login", new LoginCommand() );
	}

//------------------------------------------------------------------------------
	
	public function handleEvent( event:Event )
	{
		executeCommand( event );
	}

//------------------------------------------------------------------------------

	private function executeCommand( event:Event )
	{
		var command:Command = getCommand( event.type );
		command.execute( event );
	}

//------------------------------------------------------------------------------

	private function addCommand( commandName:String, commandRef:Command )
	{
		commands[ commandName ] = commandRef;
		EventBroadcaster.getInstance().addEventListener( commandName, this );
	}

//------------------------------------------------------------------------------

	private function getCommand ( commandName ):Command
	{
		var command:Command = commands[ commandName ];
		if ( command == null )
			trace( "Command not found for " + commandName );

		return command;
	}

//------------------------------------------------------------------------------
	
	private var commands:Array;
}
