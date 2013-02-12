class com.movers.VO.CommentVO
{
  public function CommentVO()
  {
    _remoteClass = "com.movers.VO.CommentVO";
    _comment_job       = "";
    _comment_office    = "";
    _comment_delivery  = "";
  }

  public var _remoteClass:String;

  public var _job_id            : Number;
  public var _company_id        : Number;
      
  public var _comment_job       : String;
  public var _comment_office    : String;
  public var _comment_delivery  : String;
}
