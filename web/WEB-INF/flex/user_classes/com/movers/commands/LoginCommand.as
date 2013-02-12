// ActionScript Document
class com.movers.commands.LoginCommand implements Command, Responder
{
	public var delegate:LoginDelegate;

	public function LoginCommand()
	{
		this.delegate = new LoginDelegate( this );
	}
	
	public function onResult( result ) : Void 
	{
		trace( "blah" );
	}
	
	public function onFault( fault ) : Void
	{
	}
	
	public function execute( event:Event )
	{
		trace( "woohoo" );
	}
}