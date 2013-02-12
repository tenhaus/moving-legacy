import com.movers.VO.*;

class com.movers.VO.EstimateVO
{
  public var _remoteClass:String;

  public var contactVO:ContactVO;
  public var jobVO:JobVO;
  public var additional_contactVO:Additional_ContactVO;
  public var commentVO:CommentVO;
  public var local_summaryVO:Local_SummaryVO;
  public var ld_summaryVO:LD_SummaryVO;
  public var summary_informationVO:Summary_InformationVO;
  
  public var materialsVO:MaterialsVO;
  public var inventoryVO:InventoryVO;
  
  public function EstimateVO()
  {
    _remoteClass = "com.movers.VO.EstimateVO";
  }

}
