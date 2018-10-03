package com.roger.makecoffee.objects;

import com.roger.makecoffee.R;
import com.roger.makecoffee.objects.define.CoffeeKnowledge;
import com.roger.makecoffee.objects.define.CoffeeKnowledgeCollection;

import java.util.ArrayList;

public class KnowledgeData {
    private static KnowledgeData mKnowledgeData;
    private ArrayList<CoffeeKnowledgeCollection> mKnowledgeCollectionArrayList;

    private KnowledgeData() {
        mKnowledgeCollectionArrayList = new ArrayList<>();

        // fake data
        makeFakeData();
    }

    public static synchronized KnowledgeData getInstance() {
        if (mKnowledgeData == null) {
            mKnowledgeData = new KnowledgeData();
        }
        return mKnowledgeData;
    }

    public ArrayList<CoffeeKnowledgeCollection> getKnowledgeCollectionArrayList() {
        return mKnowledgeCollectionArrayList;
    }

    public int getKnowledgeDataSize() {
        return mKnowledgeCollectionArrayList.size();
    }

    public void addCoffeeKnowledgeCollection(CoffeeKnowledgeCollection collection) {
        mKnowledgeCollectionArrayList.add(collection);
    }

    private void makeFakeData() {
        basicKnowledge();
        findCoffee();

        basicKnowledge();
        findCoffee();
    }

    private void basicKnowledge() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("豆子到杯子");
        collection.setDrawableId(R.drawable.knowledge_coffee_bean);
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "咖啡豆種類",
                R.drawable.knowledge_coffee_bean_01,
                "目前世界上有超過100 種咖啡，其中最普遍的兩種就是阿拉比卡（Arabica）和羅布斯塔（Robusta/Canephora）。\n阿拉比卡（Arabica）：貴、味道柔順、咖啡因低\n" +
                        "咖啡廣告裡常常強調他們使用百分之百的阿拉比卡咖啡。沒錯，單用價錢來看的話，阿拉比卡的確比較高級，一般阿拉比卡咖啡豆的價錢是羅布斯塔的兩倍。\n" +
                        "\n" +
                        "就成分來看，阿拉比卡的咖啡因含量低（0.9-1.2%），脂肪含量比羅布斯塔多了60%，糖的含量則是兩倍，因此綜合起來，阿拉比卡的味道比較甜、柔和，帶點梅果般的酸味。\n" +
                        "\n" +
                        "此外，阿拉比卡的綠原酸較低一些（5.5-8%），而綠原酸除了可以抗氧化，也是抵抗害蟲的重要成分，因此阿拉比卡較容易受害蟲侵害，也容易受氣候影響，一般種植在較高海拔的地方，結的果實較少也較慢。果實為橢圓形。\n" +
                        "\n" +
                        "目前阿拉比卡最大的種植國是巴西，哥倫比亞則只產阿拉比卡咖啡。\n" +
                        "\n" +
                        "羅布斯塔（Robusta）：便宜、味道苦烈、咖啡因高\n" +
                        "相較之下，咖啡因較高（1.6-2.4%）、脂肪和糖分含量較低的羅布斯塔的味道較苦也較強烈，有人甚至不客氣地說有橡膠味。\n" +
                        "\n" +
                        "羅布斯塔的綠原酸含量較高（7-10%），不易受害蟲和氣候影響，一般種植海拔較低，結的果實多且速度很快。果實為圓型。\n" +
                        "\n" +
                        "目前羅布斯塔最大的種植國是越南，非洲和印度也都有生產。"
        ));

        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "咖啡的風味",
                R.drawable.knowledge_coffee_01,
                "在進入正題前，先來認識幾個形容咖啡風味的專有名詞：\n" +
                        "\n" +
                        "酸度（acidity）：喝咖啡時，舌頭邊緣感受到的刺激。和檸檬那種酸不一樣，而是咖啡提振味覺的一種清新、爽朗感，有時又被稱為明亮度（brightness）。酸度是咖啡很重要的一個特質，沒有酸度的咖啡味道會很平淡。\n" +
                        "香氣（aroma）：咖啡沖煮後的香味，比舌頭能感受到的味道更為多樣化。常用來形容咖啡香氣的形容詞有果香（fruit-like）、土地芬芳（earthy）、煙熏（smoky）、花朵香（flowery）、莓果（berries）、堅果（nuts）等等。\n" +
                        "醇度（body）：咖啡在口中的口感，從清淡如水或脫脂乳，到濃稠如牛奶或奶油、糖漿都有。\n" +
                        "餘味（aftertaste）：和品酒的概念相似，指咖啡喝下去後嘴巴裡殘留的味道。有些咖啡有可可或巧克力的餘味，有些則有水果、莓果、堅果等等。\n" +
                        "平衡度（balance）：這是對咖啡整體味道的評價，好的咖啡豆味道均衡、有層次，並且香氣柔和；不好的咖啡豆則通常只呈現單一味道。\n" +
                        "除了以上的專有名詞，還有幾個常見的、用來形容高品質咖啡的形容詞：\n" +
                        "\n" +
                        "香醇（mellow）：指低至中酸度，平衡性好的咖啡。\n" +
                        "溫和（mild）：表示咖啡具有調和、細緻的風味，通常指生長于高海拔的南美洲高級咖啡。\n" +
                        "柔潤（soft）：指低酸度，帶點甜味的咖啡，通常指印尼咖啡。\n" +
                        "認識了形容咖啡風味的名詞後，我們來看看哪些因素會影響到咖啡的味道吧。\n" +
                        "\n" +
                        "1.咖啡豆的產地\n" +
                        "咖啡豆產地風情\n" +
                        "\n" +
                        "咖啡老行家都知道：喝酒看年份，喝咖啡看產地。產地的土壤、氣候、海拔高度都是形塑咖啡味道最根本的元素。不同產地種出的咖啡，味道自然大大不同。以下我們為你整理了幾個主要產地的咖啡味道特色：\n" +
                        "\n" +
                        "巴西：大部分產羅布斯塔，口感濃烈，有巧克力餘味。大賣場的咖啡產品通常來自巴西。\n" +
                        "中美洲、哥倫比亞：味道比較淡，平衡度好，低酸度，餘味有果香。在美國比較流行。\n" +
                        "印尼：有泥土芳香或煙熏味，有苦可可餘味，醇度厚實。\n" +
                        "衣索比亞：咖啡原產地，種類多樣性高。許多被形容有糖漿味，並有草莓或藍莓的餘味。\n" +
                        "肯亞：味道厚實，有點番茄酸味。\n" +
                        "夏威夷：帶甜香、溫和、香醇、柔潤。"
        ));

        addCoffeeKnowledgeCollection(collection);
    }

    private void findCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("尋找咖啡");
        collection.setDrawableId(R.drawable.knowledge_coffee_02);
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "咖啡豆種類",
                R.drawable.knowledge_coffee_bean_01,
                "目前世界上有超過100 種咖啡，其中最普遍的兩種就是阿拉比卡（Arabica）和羅布斯塔（Robusta/Canephora）。\n阿拉比卡（Arabica）：貴、味道柔順、咖啡因低\n" +
                        "咖啡廣告裡常常強調他們使用百分之百的阿拉比卡咖啡。沒錯，單用價錢來看的話，阿拉比卡的確比較高級，一般阿拉比卡咖啡豆的價錢是羅布斯塔的兩倍。\n" +
                        "\n" +
                        "就成分來看，阿拉比卡的咖啡因含量低（0.9-1.2%），脂肪含量比羅布斯塔多了60%，糖的含量則是兩倍，因此綜合起來，阿拉比卡的味道比較甜、柔和，帶點梅果般的酸味。\n" +
                        "\n" +
                        "此外，阿拉比卡的綠原酸較低一些（5.5-8%），而綠原酸除了可以抗氧化，也是抵抗害蟲的重要成分，因此阿拉比卡較容易受害蟲侵害，也容易受氣候影響，一般種植在較高海拔的地方，結的果實較少也較慢。果實為橢圓形。\n" +
                        "\n" +
                        "目前阿拉比卡最大的種植國是巴西，哥倫比亞則只產阿拉比卡咖啡。\n" +
                        "\n" +
                        "羅布斯塔（Robusta）：便宜、味道苦烈、咖啡因高\n" +
                        "相較之下，咖啡因較高（1.6-2.4%）、脂肪和糖分含量較低的羅布斯塔的味道較苦也較強烈，有人甚至不客氣地說有橡膠味。\n" +
                        "\n" +
                        "羅布斯塔的綠原酸含量較高（7-10%），不易受害蟲和氣候影響，一般種植海拔較低，結的果實多且速度很快。果實為圓型。\n" +
                        "\n" +
                        "目前羅布斯塔最大的種植國是越南，非洲和印度也都有生產。"
        ));

        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "咖啡的風味",
                R.drawable.knowledge_coffee_01,
                "在進入正題前，先來認識幾個形容咖啡風味的專有名詞：\n" +
                        "\n" +
                        "酸度（acidity）：喝咖啡時，舌頭邊緣感受到的刺激。和檸檬那種酸不一樣，而是咖啡提振味覺的一種清新、爽朗感，有時又被稱為明亮度（brightness）。酸度是咖啡很重要的一個特質，沒有酸度的咖啡味道會很平淡。\n" +
                        "香氣（aroma）：咖啡沖煮後的香味，比舌頭能感受到的味道更為多樣化。常用來形容咖啡香氣的形容詞有果香（fruit-like）、土地芬芳（earthy）、煙熏（smoky）、花朵香（flowery）、莓果（berries）、堅果（nuts）等等。\n" +
                        "醇度（body）：咖啡在口中的口感，從清淡如水或脫脂乳，到濃稠如牛奶或奶油、糖漿都有。\n" +
                        "餘味（aftertaste）：和品酒的概念相似，指咖啡喝下去後嘴巴裡殘留的味道。有些咖啡有可可或巧克力的餘味，有些則有水果、莓果、堅果等等。\n" +
                        "平衡度（balance）：這是對咖啡整體味道的評價，好的咖啡豆味道均衡、有層次，並且香氣柔和；不好的咖啡豆則通常只呈現單一味道。\n" +
                        "除了以上的專有名詞，還有幾個常見的、用來形容高品質咖啡的形容詞：\n" +
                        "\n" +
                        "香醇（mellow）：指低至中酸度，平衡性好的咖啡。\n" +
                        "溫和（mild）：表示咖啡具有調和、細緻的風味，通常指生長于高海拔的南美洲高級咖啡。\n" +
                        "柔潤（soft）：指低酸度，帶點甜味的咖啡，通常指印尼咖啡。\n" +
                        "認識了形容咖啡風味的名詞後，我們來看看哪些因素會影響到咖啡的味道吧。\n" +
                        "\n" +
                        "1.咖啡豆的產地\n" +
                        "咖啡豆產地風情\n" +
                        "\n" +
                        "咖啡老行家都知道：喝酒看年份，喝咖啡看產地。產地的土壤、氣候、海拔高度都是形塑咖啡味道最根本的元素。不同產地種出的咖啡，味道自然大大不同。以下我們為你整理了幾個主要產地的咖啡味道特色：\n" +
                        "\n" +
                        "巴西：大部分產羅布斯塔，口感濃烈，有巧克力餘味。大賣場的咖啡產品通常來自巴西。\n" +
                        "中美洲、哥倫比亞：味道比較淡，平衡度好，低酸度，餘味有果香。在美國比較流行。\n" +
                        "印尼：有泥土芳香或煙熏味，有苦可可餘味，醇度厚實。\n" +
                        "衣索比亞：咖啡原產地，種類多樣性高。許多被形容有糖漿味，並有草莓或藍莓的餘味。\n" +
                        "肯亞：味道厚實，有點番茄酸味。\n" +
                        "夏威夷：帶甜香、溫和、香醇、柔潤。"
        ));

        addCoffeeKnowledgeCollection(collection);
    }
}
