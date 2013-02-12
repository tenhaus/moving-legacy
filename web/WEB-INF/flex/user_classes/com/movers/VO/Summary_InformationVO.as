class com.movers.VO.Summary_InformationVO
{
  public function Summary_InformationVO()
  {
    _remoteClass = "com.movers.VO.Summary_InformationVO";

    _storage_fee         = 0;
    _extra_stops         = 0;
    _access              = 0;
    _bulky_items         = 0;
    _bulky_items_charge  = 0;
    _accessorial         = 0;
    _piano_charge        = 0;
    _cleaning_fee        = 0;
    _flat_rate           = 0;
    _extra_weight        = 0;
    _extra_rate          = 0;
    _materials_sale      = 0;
    _extra_materials     = 0;
    _add_in_one		 = 0;
    _add_in_two		 = 0;
    _add_in_one_text	 = "";
    _add_in_two_text	 = "";
    _subtotal            = 0;
    _discount            = 0;
    _total               = 0;
  }

  public var _remoteClass:String;

  public var _job_id              : Number;
  public var _company_id          : Number;

  public var _storage_fee         : Number;
  public var _extra_stops         : Number;
  public var _access              : Number;
  public var _bulky_items         : Number;
  public var _bulky_items_charge  : Number;
  public var _accessorial         : Number;
  public var _piano_charge        : Number;
  public var _cleaning_fee        : Number;
  public var _flat_rate           : Number;
  public var _extra_weight        : Number;
  public var _extra_rate          : Number;
  public var _materials_sale      : Number;
  public var _extra_materials     : Number;
  public var _add_in_one	  : Number;
  public var _add_in_two	  : Number;
  public var _add_in_one_text	  : String;
  public var _add_in_two_text	  : String;
  public var _subtotal            : Number;
  public var _discount            : Number;
  public var _total               : Number;
}
