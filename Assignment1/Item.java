

public class Item{
    
    private ItemType type;
    private int value = 0;

    public Item(ItemType type, int value){
        this.type = type;
        this.value = value;
    }

    public static Item Add(){
        return new Item(ItemType.ADD, 0);
    }
    public static Item Sub(){
        return new Item(ItemType.SUB, 0);
    }
    public static Item Mul(){
        return new Item(ItemType.MUL, 0);
    }
    public static Item Div(){
        return new Item(ItemType.DIV, 0);
    }
    public static Item Value(int value){
        return new Item(ItemType.VALUE, value);
    }

    public ItemType type(){
        return this.type;
    }
    public int value(){
        return this.value;
    }
}

   