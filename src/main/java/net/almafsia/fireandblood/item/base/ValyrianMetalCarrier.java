package net.almafsia.fireandblood.item.base;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ValyrianMetalCarrier extends Hashtable <Item, Item>  {
    public static HashMap<Item, List<Item>> METALS = new HashMap<>(){{
        put(null, new ArrayList<Item>());
        put(Items.IRON_INGOT, Arrays.asList(
                Items.DIAMOND,
                Items.HEART_OF_THE_SEA));
        put(Items.GOLD_INGOT, Arrays.asList(
                Items.DIAMOND,
                Items.HEART_OF_THE_SEA));
        put(Items.COPPER_INGOT, Arrays.asList(
                Items.DIAMOND,
                Items.HEART_OF_THE_SEA));
    }};

    public static HashMap<Item, List<Item>> ADDITION_INCOMPATIBILITIES= new HashMap<>(){{
       put(null, new ArrayList<Item>());
       put(Items.DIAMOND, Arrays.asList(Items.AZALEA, Items.DANDELION));
       put(Items.DANDELION, Arrays.asList(Items.DIAMOND, Items.AMETHYST_SHARD));
       put(Items.AZALEA, Arrays.asList(Items.DIAMOND, Items.AMETHYST_SHARD));
       put(Items.AMETHYST_SHARD, Arrays.asList(Items.AZALEA, Items.DANDELION));
       put(Items.HEART_OF_THE_SEA, new ArrayList<Item>());
    }};

    public static List<Item> ACCEPTABLE_ADDITIONS = Arrays.asList(
            Items.DIAMOND,
            Items.DANDELION,
            Items.AZALEA,
            Items.AMETHYST_SHARD,
            Items.HEART_OF_THE_SEA);
    public final Item baseMetal;
    private final int maxMetalsContained=4;
    private int metalsContained=0;

    public ValyrianMetalCarrier(Item baseMetal) throws IllegalArgumentException{
        if (checkIfMetal(baseMetal)) {
            this.baseMetal = baseMetal;
            if(!baseMetal.equals(null)) this.metalsContained=1;
        }
        else throw new IllegalArgumentException();
    }

    public static boolean checkIfAdditionAcceptable(Item addition) {
        return ACCEPTABLE_ADDITIONS.contains(addition);
    }

    public static boolean checkIfMetal(Item metal){
        return METALS.containsKey(metal);
    }

    public boolean addMetal(Item metal) throws IllegalArgumentException{
        if (!checkIfMetal(metal)) throw new IllegalArgumentException();
        if (metalsContained<maxMetalsContained) {
            this.put(metal, null);
            metalsContained++;
            return true;
        }
        return false;
    }

    public boolean addAddition(Item addition) throws IllegalArgumentException{
        if (!checkIfAdditionAcceptable(addition)) throw new IllegalArgumentException();
        if (this.containsValue(addition)) return false;

        AtomicBoolean skipper = new AtomicBoolean(false);
        this.forEach((metal, added)->{
            if (added.equals(null)) {
                if (checkCompatibility(metal, addition)&& !skipper.get()) {
                    this.replace(metal, addition);
                    skipper.set(true);
                }
            }
        });
        return this.containsValue(addition);
    }

    public boolean checkCompatibility(Item metal, Item addition) {
        boolean metalCompatibility=METALS.get(metal).contains(addition);
        boolean addedCompatibility=false;
        for(Item i: this.values()){
            if (i.equals(null)) continue;
            addedCompatibility=addedCompatibility||ADDITION_INCOMPATIBILITIES.get(i).contains(addition);
        }

        return metalCompatibility&&addedCompatibility;
    }

    public
}
