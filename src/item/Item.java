//package item;
//
//
///*
//  @author   george
//  @project   security25
//  @class  Item
//  @version  1.0.0
//  @since 18.02.25 - 19.41
//*/
//
//import lombok.*;
//import org.springframework.data.annotation.Id;
//
//@Data
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString
//public class Item {
//
//    @Id
//    private String id;
//    private String name;
//    private String description;
//
//    public Item(String name, String description) {
//        this.name = name;
//        this.description = description;
//    }
//
//    @Override
//    public final boolean equals(Object o) {
//        if (!(o instanceof Item item)) return false;
//
//        return getId().equals(item.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getId().hashCode();
//    }
//
//}
