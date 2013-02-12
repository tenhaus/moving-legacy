class com.movers.VO.JobVO
{
  public function JobVO()
  {
    _remoteClass = "com.movers.VO.JobVO";

    _job_type          = "";
    _status            = "";
    _storage_location  = "";
    _from_time         = "";
    _to_time           = "";
    _from_date         = "";
    _to_date           = "";
    _agent             = "";
    _trip_number       = "";
    _truck_number      = "";
    _taken_by          = "";
    _booked_by         = "";
    _confirmed_by      = "";
    _adv               = "";
    _taken_date        = "";
  }

  public var _remoteClass:String;

  public var _job_id             : Number;
  public var _company_id         : Number;

  public var _agents             : Array;

  public var _job_type           : String;
  public var _status             : String; 
  public var _storage_location   : String; 
  public var _from_time          : String;
  public var _to_time	         : String;
  public var _from_date          : String;
  public var _to_date            : String;
  public var _agent              : String;
  public var _trip_number        : String;
  public var _truck_number       : String;
  public var _taken_by	         : String;
  public var _booked_by	         : String;
  public var _confirmed_by	 : String;
  public var _adv	         : String;
  public var _taken_date         : String;
}
