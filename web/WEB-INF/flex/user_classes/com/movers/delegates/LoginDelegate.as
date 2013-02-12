// ActionScript Document
class com.movers.delegates.LoginDelegate
{
	private var responder:Responder;
	private var delegate:Object;
	
	public function LoginDelegate( callingCommand:Responder )
	{
		this.delegate = mx.core.Application.application.services.loginDelegate;
		this.responder = callingCommand;
	}
	
	public function login():Void
	{
		var pendingCall = delegate.login();
		pendingCall.resultHandler = mx.utils.Delegate.create( Object(this.responder), this.resonder.onResult );
	}
}