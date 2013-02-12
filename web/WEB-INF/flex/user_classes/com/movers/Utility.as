import com.movers.VO.*;

class com.movers.Utility
{

  static var dayOfWeek_array:Array = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
  
  public function Utility()
  {
  }

  public function subtractDay( d:Date ) :Date
  {
    var day:Number   = d.getDate();
    var year:Number  = d.getFullYear();
    var month:Number = d.getMonth();
    day = day - 1;
    var d2:Date = new Date( year, month, day );
    return( d2 );
  }
  
  public function addDay( d:Date ) :Date
  {
    var day:Number   = d.getDate();
    var year:Number  = d.getFullYear();
    var month:Number = d.getMonth();
    day = day + 1;
    var d2:Date = new Date( year, month, day );
    return( d2 );
  }
  
  public static function stripFormatting( num:String ) : Number
  { 
    if( num == null )
    {
      return(0);
    }
    var result:String = "";
    for( var i=0; i<num.length; i++ )
    {
      if( num.charAt(i) != '$' && num.charAt(i) != ',' )
      {
        result += num.charAt(i);
      }
    }
    
    if( Number(result) )
    {
      return( Number(result) );
    }
    else
    {
      return(0);
    }
  }
  
  function findIndex( comp, itemvalue ) {
    var blah:Array = mx.utils.ArrayUtil.toArray( comp.dataProvider );
    for( var i:Number = 0; i < blah.length; i++ ) {
      var tmp = blah[i].label;
      if( String(tmp) == String(itemvalue) ) {
        return( i );
      }
    }
    return( 0 );
  }

  public static function findStateIndex( comp, itemvalue ) {
    var blah:Array = mx.utils.ArrayUtil.toArray( comp.dataProvider );
    for( var i:Number = 0; i < blah.length; i++ ) {
      var tmp = blah[i].data;
      if( String(tmp) == String(itemvalue) ) {
        return( i );
      }
    }
    return( 0 );
  }
									      

  function findMoversIndex( comp, itemvalue ) {
    var blah:Array = mx.utils.ArrayUtil.toArray( comp.dataProvider );
    for( var i:Number = 0; i < blah.length; i++ ) {
      var tmp = blah[i].data;
      if( String(tmp) == String(itemvalue) ) {
        return( i );
      }
    }
    return( 0 );
  }

  
  static function getHoursAmPm(hour24:Number):Object {
    var returnObj:Object = new Object();
    returnObj.ampm = (hour24<12) ? "AM" : "PM";
    var hour12:Number = hour24%12;
    if (hour12 == 0) {
      hour12 = 12;
    }
    returnObj.hours = hour12;
    return returnObj;
  }
  
  function hardCopyParams( hardcopy, job:Open_JobVO )
  {
    hardcopy.form="dfg";
    hardcopy.jobnumber = job.job_id;
    hardcopy.fromcounty = job.contactVO._from_county;
    
    hardcopy.fromcity = job.contactVO._from_city
    hardcopy.tocounty = job.contactVO._to_county
    hardcopy.tocity   = job.contactVO._to_city;
    
    hardcopy.company = job.sessionVO._company_name;
    
    hardcopy.salesman = job.jobVO._booked_by;
    hardcopy.jobtype = job.jobVO._job_type;
    hardcopy.date = job.jobVO._from_date
    hardcopy.time = "From " + job.jobVO._from_time + " to " + job.jobVO._to_time;
    
    hardcopy.fromname = job.contactVO._from_name
    hardcopy.fromaddress1 = job.contactVO._from_address_1;
    hardcopy.fromaddress2 = job.contactVO._from_address_2;
    hardcopy.fromstate = job.contactVO._from_state;
    hardcopy.fromzip = job.contactVO._from_zip;
    hardcopy.fromhomephone = job.contactVO._from_home_phone;
    hardcopy.fromworkphone = job.contactVO._from_work_phone;
    hardcopy.fromcellphone = job.contactVO._from_cell_phone;
    
    hardcopy.toname = job.contactVO._to_name;
    hardcopy.toaddress1 = job.contactVO._to_address_1;
    hardcopy.toaddress2 = job.contactVO._to_address_2;
    hardcopy.tostate = job.contactVO._to_state;
    hardcopy.tozip = job.contactVO._to_zip;
    hardcopy.tohomephone = job.contactVO._to_home_phone;
    hardcopy.toworkphone = job.contactVO._to_work_phone
    hardcopy.tocellphone = job.contactVO._to_cell_phone
    
    hardcopy.nummovers = job.local_summaryVO._movers;
    hardcopy.numtrucks =  job.local_summaryVO._trucks;
    hardcopy.localrate =  job.local_summaryVO._per_hour;
    hardcopy.localhours= job.local_summaryVO._total_hours;
    hardcopy.traveltime =  job.local_summaryVO._travel_time;
    hardcopy.localsubtotal =  job.local_summaryVO._subtotal;
    hardcopy.localdiscount =  job.local_summaryVO._discount;
    hardcopy.localfee =  job.local_summaryVO._total;
    
    hardcopy.weight =  job.ld_summaryVO._weight;
    hardcopy.minimumweight =  job.ld_summaryVO._minimum_weight;
    hardcopy.minimumrate =  job.ld_summaryVO._minimum_price;
    hardcopy.pricepercubic =  job.ld_summaryVO._price_per_cubic_feet;
    hardcopy.priceperpound =  job.ld_summaryVO._price_per_pound;
    hardcopy.longsubtotal =    job.ld_summaryVO._subtotal;
    hardcopy.longdiscount =    job.ld_summaryVO._discount;
    hardcopy.longtotal = 	 job.ld_summaryVO._total;
    
    hardcopy.comments =  job.commentVO._comment_job;
    
    hardcopy.piano =  job.summary_informationVO._piano_charge;
    hardcopy.extraweight = job.summary_informationVO._extra_weight;
    hardcopy.extrabox = job.summary_informationVO._extra_materials;
    //hardcopy.travel = job.summary_informationVO._t
    hardcopy.storage = job.summary_informationVO._storage_fee;
    hardcopy.material = job.summary_informationVO._materials_sale;
    
    hardcopy.addinonetext = job.summary_informationVO._add_in_one_text;
    hardcopy.addinone = job.summary_informationVO._add_in_one;
    hardcopy.addintwotext = job.summary_informationVO._add_in_two_text;
    hardcopy.addintwo = job.summary_informationVO._add_in_two;
    
    hardcopy.flatrate = job.summary_informationVO._flat_rate;
    hardcopy.totaldiscount = job.summary_informationVO._discount;
    hardcopy.total = job.summary_informationVO._total;
    
    return( hardcopy );
  }
  
  function orderForServiceParams( orderforservice, job:Open_JobVO ) {
  
    orderforservice.form = "orderforservice";
    orderforservice.jobnumber = job.job_id;		
    
    orderforservice.fromname = job.contactVO._from_name;
    orderforservice.fromaddress = job.contactVO._from_address_1;
    orderforservice.fromcity = job.contactVO._from_city;
    orderforservice.fromstate = job.contactVO._from_state + " " + job.contactVO._from_zip;
    
    orderforservice.toname = job.contactVO._to_name;
    orderforservice.toaddress = job.contactVO._to_address_1;
    orderforservice.tocity = job.contactVO._to_city;
    orderforservice.tostate = job.contactVO._to_state + " " + job.contactVO._to_zip;
    
    orderforservice.date = job.jobVO._from_date;
    
    return( orderforservice );
  }
  
  function longContractParams( longcontract, job:Open_JobVO ) {
  
    longcontract.form = "longcontract";
    longcontract.jobnumber = job.job_id;		
    
    longcontract.fromname = job.contactVO._from_name;	
    longcontract.fromaddress = job.contactVO._from_address_1;
    longcontract.fromphone = job.contactVO._from_address_2;
    longcontract.fromcity = job.contactVO._from_city;
    longcontract.fromcounty = job.contactVO._from_county;
    longcontract.fromstate = job.contactVO._from_state + " " + job.contactVO._from_zip;
    
    longcontract.toname = job.contactVO._to_name;		
    longcontract.toaddress = job.contactVO._to_address_1;
    longcontract.tophone = job.contactVO._to_home_phone;
    longcontract.tocity = job.contactVO._to_city;
    longcontract.tocounty = job.contactVO._to_county;
    longcontract.tostate = job.contactVO._to_state + " " + job.contactVO._to_zip;
    
    longcontract.date = job.jobVO._from_date;
    longcontract.time = job.jobVO._from_time;
    
    longcontract.weight = job.ld_summaryVO._weight;
    longcontract.total = "$" + job.summary_informationVO._total;
    
    return( longcontract );
  }
  
  function localContractParams( localcontract, job:Open_JobVO ) {
  
    localcontract.form = "localcontract";
    localcontract.jobnumber = job.job_id;		
    localcontract.fromname = job.contactVO._from_name;
    localcontract.fromphone = job.contactVO._from_home_phone;
    localcontract.fromaddress = job.contactVO._from_address_1;
    localcontract.fromcity = job.contactVO._from_city;
    localcontract.fromstate = job.contactVO._from_state;
    localcontract.fromzip = job.contactVO._from_zip;
    
    localcontract.toaddress = job.contactVO._to_address_1;    
    localcontract.tocity = job.contactVO._to_city;
    localcontract.tostate = job.contactVO._to_state;
    localcontract.tozip = job.contactVO._to_zip;
    
    localcontract.date = job.jobVO._from_date;
    localcontract.time = job.jobVO._from_time;
    
    localcontract.trucks = job.local_summaryVO._trucks;
    localcontract.men    = job.local_summaryVO._movers; 
    localcontract.rate = "$" + job.local_summaryVO._per_hour;
    localcontract.traveltime = job.local_summaryVO._travel_time + "/Min";
    
    return( localcontract );
  }
  
  function invoiceParams( invoice, job:Open_JobVO ) {
  
    invoice.company_name = job.sessionVO._company_name;
    invoice.company_address =  job.sessionVO._company_address + "\n" + job.sessionVO._company_city;
    invoice.company_address += ", " + job.sessionVO._company_state + " " + job.sessionVO._company_zip;
    
    var paid:Number = 0;
    
    invoice.form = "invoice";
    invoice.invoice = job.job_id;
    invoice.date = job.jobVO._from_date;
    
    var payment_date:String = "";
    var payment_amount:String = "";
    var payment_type:String = "";
    var payment_desc:String = "";
    
    
    for( var i=0; i<job.paymentVO._paymentList.length; i++ ) {
      var rec = job.paymentVO._paymentList.getItemAt(i);
      payment_date += rec.date + "\n";
      payment_amount += rec.amount + "\n";
      payment_type += rec.type + "\n";
      payment_desc += rec.description + "\n";
      paid += Number( stripFormatting(rec.amount) );
    }

    invoice.payment_date = payment_date;
    invoice.payment_amount = payment_amount;
    invoice.payment_type = payment_type;
    invoice.payment_desc = payment_desc;
    
    invoice.billto = job.contactVO._from_name;
    invoice.address1 = job.contactVO._to_address_1;
    invoice.address2 = job.contactVO._to_address_2;
    invoice.city = job.contactVO._to_city;
    invoice.state = job.contactVO._to_state;
    invoice.zip = job.contactVO._to_zip;
    invoice.fromaddress = job.contactVO._from_address_1 + " " + job.contactVO._from_state + " " + job.contactVO._from_zip;
    invoice.toaddress =   job.contactVO._to_address_1 + " " + job.contactVO._to_state + " " + job.contactVO._to_zip;
    invoice.cubicfeet = job.ld_summaryVO._total_cubic_feet;
    invoice.percub = job.ld_summaryVO._price_per_cubic_feet;
    invoice.totalhours = job.local_summaryVO._total_hours;
    
    invoice.hourlyrate = job.local_summaryVO._per_hour;
    invoice.subld = job.ld_summaryVO._total;
    invoice.sublocal = job.local_summaryVO._total;
    invoice.boxdelivery = job.summary_informationVO._materials_sale;
    invoice.packing = "0.00";
    invoice.unpacking = "0.00";
    invoice.travelfee = "0.00"
    invoice.storagefee = job.summary_informationVO._storage_fee;
    invoice.piano = job.summary_informationVO._piano_charge;
    invoice.extracub = (Number(job.summary_informationVO._extra_weight) * 7);
    invoice.handling = "0.00";
    invoice.extrainsurance = "0.00";
    invoice.other = "0.00";
    invoice.addinonedesc = job.summary_informationVO._add_in_one_text;
    invoice.addinone = job.summary_informationVO._add_in_one;
    invoice.addintwodesc = job.summary_informationVO._add_in_two_text;
    invoice.addintwo = job.summary_informationVO._add_in_two;
    invoice.flatrate = "0.00";
    invoice.discount = job.summary_informationVO._discount;
    invoice.total = job.summary_informationVO._total;
    invoice.comments = job.commentVO._comment_job;
    
    invoice.balance = Number(invoice.total) - Number(paid);
    invoice.totalpaid = paid;
    
    return( invoice );
  }

  function closedJobParams( closedjob, job:Open_JobVO ) {
    
    closedjob.form="closed";
    closedjob.job_number = job.job_id;		
    closedjob.comment = job.commentVO._comment_job;
    closedjob.date    = job.jobVO._from_date;
    
    closedjob.fromname = job.contactVO._from_name;
    closedjob.fromaddress = job.contactVO._from_address_1 + " " + job.contactVO._from_address_2;
    closedjob.fromcity = job.contactVO._from_city;
    closedjob.fromstate = job.contactVO._from_state;
    closedjob.fromzip = job.contactVO._from_zip;
    closedjob.fromphone = job.contactVO._from_home_phone;
    closedjob.fromwork  = job.contactVO._from_work_phone;
    
    closedjob.toname = job.contactVO._to_name;
    closedjob.toaddress = job.contactVO._to_address_1 + " " + job.contactVO._to_address_2;
    closedjob.tocity = job.contactVO._to_city;
    closedjob.tostate = job.contactVO._to_state;
    closedjob.tozip = job.contactVO._to_zip;
    closedjob.tophone = job.contactVO._to_home_phone;
    closedjob.towork = job.contactVO._to_work_phone;
    
    closedjob.movers = job.local_summaryVO._movers;
    closedjob.trucks = job.local_summaryVO._trucks;
    closedjob.rate   = job.local_summaryVO._per_hour;
    closedjob.hours = job.local_summaryVO._total_hours;
    closedjob.travel_time = job.local_summaryVO._travel_time;
    closedjob.local_sub_total = job.local_summaryVO._subtotal;
    closedjob.local_discount = job.local_summaryVO._discount;
    closedjob.local_fee = job.local_summaryVO._total;
    
    closedjob.weight = job.ld_summaryVO._weight;
    closedjob.cuft = job.ld_summaryVO._total_cubic_feet;
    closedjob.ppp = job.ld_summaryVO._price_per_pound;
    closedjob.long_sub_total = job.ld_summaryVO._subtotal;
    closedjob.long_discount = job.ld_summaryVO._discount;
    closedjob.long_fee = job.ld_summaryVO._total;
    
    closedjob.piano = job.summary_informationVO._piano_charge;;
    closedjob.extra_weight = job.summary_informationVO._extra_weight;
    closedjob.extra_rate = job.summary_informationVO._extra_rate;
    
    closedjob.taken_by = job.jobVO._taken_by;
    closedjob.booked_by = job.jobVO._booked_by;
    
    closedjob.travel = "0.00";
    
    closedjob.total_discount = job.summary_informationVO._discount;
    closedjob.total_fee = job.summary_informationVO._total;
    
    var payment_date:String = "";
    var payment_amount:String = "";
    var payment_type:String = "";
    var payment_desc:String = "";
    
    for( var i=0; i<job.paymentVO._paymentList.length; i++ ) {
      var rec = job.paymentVO._paymentList.getItemAt(i);
      payment_date += rec.date + "\n";
      payment_amount += rec.amount + "\n";
      payment_type += rec.type + "\n";
      payment_desc += rec.description + "\n";
    }
					    
    closedjob.payment_date = payment_date;
    closedjob.payment_amount = payment_amount;
    closedjob.payment_type = payment_type;
    closedjob.payment_desc = payment_desc;
    
    var employee:String = "";
    var hours:String = "";
    var employee_rate:String = "";
    var total:String = "";
    for( var i=0; i<job.moversVO._moversList.length; i++ ) {
      var rec = job.moversVO._moversList.getItemAt(i);
      employee += rec.employee + "\n";
      hours += rec.hours + "\n";
      employee_rate += rec.rate + "\n";
      total += rec.total + "\n";
    }
    closedjob.employee = employee;
    closedjob.employee_hours = hours;
    
    closedjob.employee_rate = employee_rate;
    closedjob.total = total;
    
    return( closedjob );
  }
}
