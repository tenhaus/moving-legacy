class com.movers.VO.EmployeeInformationVO
{
  public function EmployeeInformationVO()
  {
    _remoteClass = "com.movers.VO.EmployeeInformationVO";

    _name           = "";
    _address_1      = "";
    _address_2      = "";
    _city           = "";
    _state          = "";
    _zip            = "";
    _home_phone     = "";
    _email          = "";
    _ssn            = "";
    _hourly_rate    = 0;
    _ext            = "";
    _role           = "";

  }
  
  public var _remoteClass    : String;

  public var _username       : String;
  public var _password       : String;
  public var _name           : String;
  public var _address_1      : String;
  public var _address_2      : String;
  public var _city           : String;
  public var _state          : String;
  public var _zip            : String;
  public var _home_phone     : String;
  public var _email          : String;
  public var _ssn            : String;

  public var _hourly_rate    : Number;
  public var _ext            : String;
  public var _role           : String;
  
  public var _employee_id    : Number;
  public var _company_id     : Number;
}
