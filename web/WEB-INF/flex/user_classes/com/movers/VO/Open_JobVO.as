import com.movers.VO.*;

class com.movers.VO.Open_JobVO
{
  public var _remoteClass:String;

  public var job_id;
  public var company_id;
  
  public var sessionVO:SessionVO;

  public var contactVO:ContactVO;
  public var jobVO:JobVO;
  public var additional_contactVO:Additional_ContactVO;
  public var commentVO:CommentVO;
  public var local_summaryVO:Local_SummaryVO;
  public var ld_summaryVO:LD_SummaryVO;
  public var summary_informationVO:Summary_InformationVO;
  
  public var materialsVO:MaterialsVO;
  public var inventoryVO:InventoryVO;

  public var paymentVO:PaymentVO;
  public var moversVO:MoversVO;
  public var tripVO:TripVO;
  
  public function Open_JobVO()
  {
    _remoteClass = "com.movers.VO.Open_JobVO";
  }

}
