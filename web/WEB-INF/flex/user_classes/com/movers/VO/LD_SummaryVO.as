class com.movers.VO.LD_SummaryVO
{
  public function LD_SummaryVO()
  {
    _remoteClass = "com.movers.VO.LD_SummaryVO";
    
    _total_cubic_feet      = 0;
    _weight                = 0;
    _adjusted_weight       = 0;
    _minimum_weight        = 0;
    _minimum_price         = 0;
    _price_per_pound       = 0;
    _price_per_cubic_feet  = 0;
    _subtotal              = 0;
    _discount              = 0;
    _total                 = 0;
  }

  public var _remoteClass:String;

  public var _job_id                : Number;
  public var _company_id            : Number;
      

  public var _total_cubic_feet      : Number;
  public var _weight                : Number;
  public var _adjusted_weight       : Number;
  public var _minimum_weight        : Number;
  public var _minimum_price         : Number;
  public var _price_per_pound       : Number;
  public var _price_per_cubic_feet  : Number;
  public var _subtotal              : Number;
  public var _discount              : Number;
  public var _total                 : Number;
}
