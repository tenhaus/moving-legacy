class com.movers.VO.OwnerReportVO
{
  public function OwnerReportVO()
  {
    _remoteClass = "com.movers.VO.OwnerReportVO";
  
    _job_id = -1;
    _company_id = 0;    
    _agent_id = 0;      
    _agent = "";

    _labor_charge = 0;
    _other_1 = 0;
    _other_2 = 0;
    _other_3 = 0;
  }

  public var _remoteClass:String;

  public var _job_id              : Number;
  public var _company_id          : Number;
  public var _agent_id            : Number;
  public var _agent               : String;
  
  public var _labor_charge        : Number;
  public var _other_1             : Number;
  public var _other_2             : Number;
  public var _other_3             : Number;

}
