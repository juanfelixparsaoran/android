package android.example.homejoy;

public class Supply {
    public String supp_name;
    public Integer supply_amount;
    public String supply_id;
    public Supply(){

    }

    public Supply(String id,String supp_name, Integer supply_amount){
        this.supp_name = supp_name;
        this.supply_amount = supply_amount;
        this.supply_id = id;
    }

    public Integer getSupply_amount() {
        return supply_amount;
    }

    public String getSupp_name() {
        return supp_name;
    }

    public String getSupply_id() {
        return supply_id;
    }
}

