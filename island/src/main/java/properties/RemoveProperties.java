package properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

public class RemoveProperties {

    public void remove(Structs.Polygon.Builder builder, String key){
        for(int i = 0; i<builder.getPropertiesList().size(); i++){
            //remove that property is its key
            if(builder.getProperties(i).getKey().equals(key)){
                builder.removeProperties(i);
            }
        }
    }
}
