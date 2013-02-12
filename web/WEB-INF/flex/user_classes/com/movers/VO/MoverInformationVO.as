class com.movers.VO.MoverInformationVO
{
  public function MoverInformationVO()
  {
    _remoteClass = "com.movers.VO.MoverInformationVO";

    _name           = "";
    _address_1      = "";
    _address_2      = "";
    _city           = "";
    _state          = "";
    _zip            = "";
    _home_phone     = "";
    _cell_phone     = "";
    _other_phone    = "";
    _email          = "";
    _ssn            = "";
    _license        = "";

    _rate_f         = 0;
    _rate_d         = 0;
    _rate_h         = 0;
    _rate_s         = 0;
    _commision      = 0;
  }
  
  public var _remoteClass    : String;

  public var _name           : String;
  public var _address_1      : String;
  public var _address_2      : String;
  public var _city           : String;
  public var _state          : String;
  public var _zip            : String;
  public var _home_phone     : String;
  public var _cell_phone     : String;
  public var _other_phone    : String;
  public var _email          : String;
  public var _ssn            : String;
  public var _license        : String;
  
  public var _rate_f         : Number;
  public var _rate_d         : Number;
  public var _rate_h         : Number;
  public var _rate_s         : Number;
  public var _commision      : Number;

  public var _mover_id       : Number;
  public var _company_id     : Number;
}
