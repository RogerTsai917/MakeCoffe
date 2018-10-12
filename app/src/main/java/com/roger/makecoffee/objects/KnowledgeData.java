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
        beansToCup();   //豆子到杯子
        judgeCoffee();  //評價咖啡
//        findCoffee();   //尋找咖啡
        differentCountriesCoffee();//各國咖啡
        makeCupOfCoffee();//沖杯咖啡
//        buyCoffee();    //購買咖啡

    }

    private void beansToCup() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("豆子到杯子");
        collection.setDrawableId(R.drawable.knowledge_coffee_bean);
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "阿拉比卡和羅布斯塔",
                R.drawable.knowledge_coffee_bean_01,
                "目前世界上有超過100 種咖啡，其中最普遍的兩種就是阿拉比卡（Arabica）和羅布斯塔（Robusta/Canephora）。這兩種咖啡在味道、成分和種植條件上都大有不同，以下我們將比較兩者的差別。\n" +
                        "\n" +
                        "Robusta-Arabica-bean 阿拉比卡羅布斯塔\n" +
                        "\n" +
                        "阿拉比卡（Arabica）：價格較貴、味道柔順、咖啡因低\n" +
                        "\n" +
                        "咖啡廣告裡常常強調他們使用百分之百的阿拉比卡咖啡。沒錯，單用價錢來看的話，阿拉比卡的確比較高級，一般阿拉比卡咖啡豆的價錢是羅布斯塔的兩倍。\n" +
                        "\n" +
                        "就成分來看，阿拉比卡的咖啡因含量低（0.9-1.2%），脂肪含量比羅布斯塔多了60%，糖的含量則是兩倍，因此綜合起來，阿拉比卡的味道比較甜、柔和，帶點梅果般的酸味。\n" +
                        "\n" +
                        "此外，阿拉比卡的綠原酸較低一些（5.5-8%），而綠原酸除了可以抗氧化，也是抵抗害蟲的重要成分，因此阿拉比卡較容易受害蟲侵害，也容易受氣候影響，一般種植在較高海拔的地方，結的果實較少也較慢。果實為橢圓形。\n" +
                        "\n" +
                        "目前阿拉比卡最大的種植國是巴西，哥倫比亞則只產阿拉比卡咖啡。\n" +
                        "\n" +
                        "羅布斯塔（Robusta）：價格較便宜、味道苦烈、咖啡因高\n" +
                        "相較之下，咖啡因較高（1.6-2.4%）、脂肪和糖分含量較低的羅布斯塔的味道較苦也較強烈，有人甚至不客氣地說有橡膠味。\n" +
                        "\n" +
                        "羅布斯塔的綠原酸含量較高（7-10%），不易受害蟲和氣候影響，一般種植海拔較低，結的果實多且速度很快。果實為圓型。\n" +
                        "\n" +
                        "目前羅布斯塔最大的種植國是越南，非洲和印度也都有生產。\n" +
                        "\n" +
                        "價錢高低不等於品質\n" +
                        "\n" +
                        "因為價錢便宜，羅布斯塔常用來混於市售咖啡粉中以降低成本，目前市面上的便宜即溶咖啡，大多是羅布斯塔居多，但價錢高低不等於品質，品質好的羅布斯塔豆常被用於濃縮咖啡（espressos），因為它的crema較為豐厚。品質好的羅布斯塔，味道甚至比品質差的阿拉比卡豆要好。\n" +
                        "\n" +
                        "因此，兩種咖啡豆之間的抉擇，主要還是看個人的喜好，有些人可能覺得阿拉比卡香氣太重，有些人則喜歡羅布斯塔醇厚的苦味。我們唯一要提醒的是，如果你對咖啡因敏感，就要特別注意咖啡因的含量，羅布斯塔的咖啡因含量是阿拉比卡的兩倍。"
        ));

        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "咖啡豆的生長環境",
                R.drawable.knowledge_coffee_bean_02,
                "咖啡樹的原產地在非洲的埃塞俄比亞。\n" +
                        "\n" +
                        "咖啡樹在植物學上，屬於茜草科咖啡亞屬的常綠樹，而一般所俗稱的咖啡豆，勘察是咖啡樹所結果實的種子，只因為形狀像豆子，所以被稱為咖啡豆。\n" +
                        "\n" +
                        "氣候是咖啡種植的決定性因素，咖啡樹只適合生長在熱帶或亞熱帶，所以南北緯25度之間的地帶，最適合栽植咖啡。而這個咖啡生產地帶，一般稱為“咖啡帶”或“咖啡區”。\n" +
                        "\n" +
                        "但是，並非所有位於此區內的土地，都能培育出優良的咖啡樹。咖啡樹最理想的種植條件為：溫度介於15-25℃之間的溫暖氣候，而且整年的降雨量必須達1500-2000毫米，同時其降雨時間，要能配合咖啡樹的開花週期。當然，除了季節和雨量的配合外，還要有肥活的土壤。最適合栽培咖啡的土壤，是排水良好，含火山灰質的肥活土壤。\n" +
                        "\n" +
                        "咖啡帶：咖啡不是在任何環境下都能種植的，因其原是熱帶雨林環境下生長的植物，在系統發育中，形成需要靜風、溫涼、蔭蔽或半蔭蔽及濕潤環境的習性。\n" +
                        "\n" +
                        "　　溫度：對溫度的要求隨栽培種類而異，小粒種較耐寒，喜溫涼的氣候，要求年平均溫度19~21度。\n" +
                        "\n" +
                        "　　雨量：年降雨量在1250毫米以上，分佈均勻，特別在花期及小果發育期，有一定降雨量最適宜咖啡的生長發育。\n" +
                        "\n" +
                        "　　光照：咖啡樹不耐強光，需要適當的蔭蔽，光照過強，生長受到抑制。如果加上水肥不足，就會出現早衰現象，甚至死亡。蔭蔽過度，枝葉太長，花果稀少，產量很低。\n" +
                        "\n" +
                        "　　風：咖啡喜歡靜風環境。\n" +
                        "\n" +
                        "　　土壤：咖啡樹根係發達，吸收根分佈淺，要求疏鬆、肥沃、排水良好的壤土。\n" +
                        "\n" +
                        "世界上大多數的咖啡收採是以手來挑選，因此，收採咖啡是一項耗費人力和季節性密集的過程。\n" +
                        "\n" +
                        "由於在同一根樹枝上同時會有花及果實的存在，因此，採集者的食指與拇指就是收集成熟漿果的最佳工具。\n" +
                        "\n" +
                        "用手將整條樹枝上的果實刮下，或是使用自動化的採收機，都無法將成熟與青澀的漿果區分開來。\n" +
                        "\n" +
                        "生產低級咖啡豆的咖啡農喜歡使用節省人工的方法採豆子，但如此一來，因為品質不純正，減損了咖啡風味，降低咖啡等級。\n" +
                        "\n" +
                        "非洲有些地區採咖啡豆的方法是搖晃咖啡樹，把果實搖落地面，在果實受傷爛掉之前從地上撿起。巴西大部分地區生產次級咖啡，這些地區採咖啡的方法是一次將枝幹上所有葉子、花朵、過熱與青澀果子統統摘下，被如此殘害的咖啡樹需要兩年的時間才能恢復正常。"
        ));

        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "咖啡豆烘焙",
                R.drawable.knowledge_coffee_bean_03,
                "咖啡生豆所含的七、八百種芬香成份必須要靠專業烘焙來喚醒，少了這道烘焙的程序，咖啡只不過是一些不太舒服的日曬味或水洗味，更不用說要拿來當成飲用品了。\n" +
                        "\n" +
                        "生豆經過五至七分鐘強力的加熱之後，會引起劇烈的化學變化，進而散發特殊的濃香，這種複雜的化學反應就連最精密的實驗室也無法模擬，不過，大概可歸納為幾個要項。\n" +
                        "\n" +
                        "首先，生豆經過攝氏二百度以上烘焙，水分開始蒸發，體積膨脹百分之六十，重量則減輕約百分之二十左右，要視烘焙程度而定，烘焙愈深，重量損失愈多。\n" +
                        "\n" +
                        "另一個重要現象是排氣，生豆在烘焙過程中會釋放出二氧化碳coffeebyroast，咖啡豆在烘焙過後還會繼續進行排氣。\n" +
                        "\n" +
                        "排放二氧化碳有助於咖啡豆的保鮮，因為在排氣的過程當中，氧分子不易入侵，也就是影響咖啡新鮮度的氧化作用無法進行，不過在烘焙過後排氣現象會逐漸減緩，氧分子很容易會附著在咖啡豆的表面，咖啡很快就會走味，這就是為何咖啡豆開封之後最好儘量喝完的重要原因。\n" +
                        "\n" +
                        "咖啡豆的化學反應在烘焙時可藉著聲音、顏色及味道來判斷反應進行的程度。咖啡豆顏色會隨著持續加溫，由灰綠色轉成金黃色進而淺褐色、微量出油的赤褐色、接著是大量出油呈現油滋滋的黑褐色，如果再繼續烘下去，咖啡豆表面的油脂會變乾，呈現出黑色，並冒出大量的煙，夾雜著焦味，這是接近燃點的警訊，咖啡豆由淺轉深，是因為焦糖化和酸性物質起變化所致。\n" +
                        "\n" +
                        "聽聲音也是判斷烘焙度的依據。\n" +
                        "\n" +
                        "如果烘焙機的溫度夠熱，咖啡豆在烘焙過程會發出兩次爆裂聲響，以曼特寧和安堤瓜等耐高溫的生豆為例，七分鐘左右會發出第一爆，聲音低沈稀落，這表示熱解作用已經啟動了，也就是澱粉開始轉化成焦糖的開始，水分也隨著二氧化碳噴出豆表，發出低沈的爆裂聲，兩分鐘歸於平靜，十二分左右又發出第二爆，這個第二爆就代表咖啡豆已經差不多烘好了，接著咖啡豆從烘焙機中移出至冷卻盤，頓時香氣瀰漫!"
        ));

        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "淺焙與深焙",
                R.drawable.knowledge_coffee_bean_04,
                "了解咖啡豆烘焙時會出現的特性之後，再來談談各種烘焙度的界定及口味問題。\n" +
                        "\n" +
                        "長久以來慣以Cinnamon(最淺焙)、City(淺焙)、Fully City(深焙)及Dark roast(重深焙，包括義式和法式) 來界定烘焙程度，近年來，美國精品咖啡協會大力鼓吹以焦糖化數值(Agtron number)來判定烘焙度，也就是以紅內線來測定咖啡豆的焦糖化程度。\n" +
                        "\n" +
                        "數值愈高表示焦糖化愈低，也就是烘焙程度愈淺，數值愈低表示焦糖化愈深，亦即烘焙程度愈深，有了數據就可以更精準的了解咖啡豆的烘焙程度，不致出現各地區有不同的烘焙度標準。\n" +
                        "\n" +
                        "咖啡風味也會隨著烘焙度不同而改變，一般而言酸味和烘焙程度呈反比，如果是偏好果酸味的朋友，不妨選購烘焙度較淺的咖啡，如果您喜歡濃郁帶一點甘香的口感，可選擇重烘焙豆。\n" +
                        "\n" +
                        "另外，咖啡因含量也和烘焙度呈反比，也就是說淺焙咖啡所含的咖啡因含量會比較高，因為咖啡因會在烘焙過程中被破壞。而果香味也會隨著烘焙度加深而遞減，不過，淺焙咖啡比較不容易展現醇酒的發酵香味、刺激味和回甘的口感。但是，如果重度烘焙技術不好的話，很可能會烘出又焦又苦的咖啡。"
        ));

        addCoffeeKnowledgeCollection(collection);
    }

    private void judgeCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("咖啡的風味");
        collection.setDrawableId(R.drawable.knowledge_coffee_01);
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "醇度(Body)",
                R.drawable.knowledge_coffee_03,
                "醇度應是指咖啡在口中濃稠黏滑的觸感，可以用豐厚來形容質感濃稠的咖啡，質感單薄的咖啡喝起來就像是一般的飲料汽水一樣。\n" +
                        "\n" +
                        "醇度的感受可分為清淡如水般的淡薄，中度如全脂鮮奶的口感以及深度如糖漿般的超高濃脂狀。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "酸度(Acidity)",
                R.drawable.knowledge_coffee_03,
                "一種分佈在舌頭後側的味覺，與令人分泌唾液的檸檬酸味不太相同，這種酸度是形容一種乾淨、明亮的感受，而且酸的香味會帶給酸味更豐富的內涵。\n" +
                        "通常產自高海拔高密度之Arabica種咖啡豆會有這種酸的特性，而低海拔之Robusta咖啡豆則沒有此種特性。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "苦味(Bitter)",
                R.drawable.knowledge_coffee_03,
                "這是深焙豆的特徵，和酸味一樣，不一定會令人感到不愉悅。一般說來酸味較強的咖啡，苦味會較弱，通常會形成苦味的原因有下列五種：\n" +
                        "\n" +
                        "    品種：通常Robusta比Arabica苦味強。\n" +
                        "\n" +
                        "    產區：通常產自印尼蘇門答臘、爪哇的咖啡停比其它產區的咖啡苦味強。\n" +
                        "\n" +
                        "    烘焙度：深烘焙比淺烘焙的咖啡豆苦，原因為焦糖化及碳化的程度較重。\n" +
                        "\n" +
                        "    咖啡因：Robusta比Arabica苦，Robusta比Arabica多出二倍的咖啡因，因此Robusta比Arabica苦。\n" +
                        "    \n" +
                        "    萃取時間：萃取時間愈長，苦味愈明顯。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "甘度(Sweet)",
                R.drawable.knowledge_coffee_03,
                "形容咖啡入口之後回甘轉甜的完美感受，令人聯想到糖漿，這種回味無窮的的美味口感也正是優質咖啡才能散發出來的風味。\n" +
                        "\n" +
                        "新鮮是造成回甘最主要的因素，香氣從喉頭湧上一股酥麻的感覺持續約二三分鐘，這種令人陶醉的餘味會一直讓你念念不忘。\n" +
                        "\n" +
                        "在烘焙與沖泡過程中，如果處理過度會很容易喪失這種咖啡的甘甜味。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "香氣(Aroma)",
                R.drawable.knowledge_coffee_03,
                "香味是咖啡品質的重要指標，產地的氣候、處理過程及烘焙技術，都是會左右咖啡豆香味的重要條件，香氣是指沖煮完成的咖啡所發出的味道，用來形容咖啡氣味包括有焦糖味、可可味、水果味、花香味、濃郁、香辛味等。一般來說，香氣的消失正意味著品質變差。"
        ));


        addCoffeeKnowledgeCollection(collection);
    }

    private void findCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("");
        collection.setDrawableId(R.drawable.knowledge_coffee_bean);
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "",
                R.drawable.knowledge_coffee_bean_01,
                ""
        ));


        addCoffeeKnowledgeCollection(collection);
    }

    private void differentCountriesCoffee() {
        asiaCoffee();
        africaCoffee();
        centralAmericaCoffee();
        southAmericaCoffee();
        oceaniaCoffee();
        caribbeanSeaCoffee();
    }

    private void asiaCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("亞洲單品咖啡");
        collection.setDrawableId(R.drawable.knowledge_world_asia);

        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "印尼(Indonesia)",
                R.drawable.knowledge_world_indonesia,
                "知名代表性咖啡: 爪哇(Java), 蘇門達臘曼特寧(Sumatra Mandheling)\n" +
                        "\n" +
                        "印尼生產咖啡豆的區域主要在爪哇、蘇門答臘、蘇拉維西等三個島，皆屬火山地形。一般認為印尼的咖啡豆香味濃厚而酸度低，略帶一點似中藥及泥土的味道。\n" +
                        "\n" +
                        "蘇門達臘(Sumatra)山區出產的曼特寧(Mandheling)世界聞名，質感豐富。爪哇島出產的羅布斯塔(Robusta)豆有獨特的氣味，因油脂豐富而常被用來作為義式濃縮咖啡的配方之一。\n" +
                        "\n" +
                        "蘇拉維西出產的咖啡則被評為有特別的草本氣息，深沈而乾淨。特別的是，印尼山間有一種特別的動物叫作麝香貓(在台灣也有出現過，目前它被列為陽明山的代表性動物之一)。\n" +
                        "\n" +
                        "因為它使得印尼出產一種幾乎是世界上最高價的咖啡-麝香貓咖啡。這種貓喜歡吃咖啡漿果，而堅硬的咖啡豆因為無法消化最後會被排出來。\n" +
                        "在經過消化道的期間，咖啡豆經過發酵作用產生了一種獨特而複雜的香味，使得不少饕客喜歡這種具有特殊香氣的咖啡。\n" +
                        "但是因為產量極少，故售價極昂，每磅在數百美元之譜。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "印度(India)",
                R.drawable.knowledge_world_india,
                "知名代表性咖啡: 季風馬拉巴(Monsooned Malabar)\n" +
                        "\n" +
                        "印度是以飲茶為主的的國家，但其咖啡生產量卻高居世界前幾名，且兼具羅布斯塔(Robusta)及阿拉比卡(Arabica)兩種，也是幾個同時存在水洗法及日曬法處理的國家之一。\n" +
                        "\n" +
                        "印度咖啡栽種的區域主要在印度南部的西高止山到阿拉伯海間的區域，較知名的有以麥索及馬拉巴等為名稱銷售的咖啡。\n" +
                        "\n" +
                        "季風馬拉巴是印度頗有特色的一種咖啡，這種咖啡當年因為由馬拉巴海岸運出口到歐洲的咖啡豆，因船行時生豆長時間受到海風吹襲後使得外觀和口感均有所改變，而變成歐洲人習慣且喜歡的口味。\n" +
                        "\n" +
                        "這後來成為一種特別的咖啡豆處理手法，咖啡豆採收後，特別將其放置在潮濕的季風中幾個月的時間，使其變為黃白的顏色且酸度降低，即是我們買到的季風馬拉巴(Monsooned Malabar)。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "越南(Vietnam)",
                R.drawable.knowledge_world_vietnam,
                "知名代表性咖啡: 越南咖啡\n" +
                        "\n" +
                        "越南也是全球前幾大咖啡生產國之一。\n" +
                        "\n" +
                        "目前越南絕大多數的咖啡樹都是羅布斯塔(Robusta)種，因為十九世紀中葉時出現的葉鏽病使得越南的阿拉比卡(Arabica)咖啡樹被摧毀殆盡，改種的都是耐葉鏽病害的羅布斯塔種的咖啡樹。\n" +
                        "\n" +
                        "羅布斯塔種的咖啡豆具有獨特的香味和苦味，它的咖啡因含量較阿拉比卡為高。\n" +
                        "\n" +
                        "也因為萃取比例較高的關你，它常被用來製作即溶咖啡，罐裝咖啡，或是摻合在三合一咖啡中等等。\n" +
                        "\n" +
                        "近年來印度也有開始種植阿拉比卡的咖啡樹，更致力於咖啡生產的事業，不過目前在精品咖啡的市場上還沒有任何發展。\n" +
                        "\n" +
                        "我們談話中常聽到的越南咖啡 有時指的是冰滴壼的煮法，和此處所提的咖啡豆種類是不同的事，可別混為一談喔。"
        ));

        addCoffeeKnowledgeCollection(collection);
    }

    private void africaCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("非洲單品咖啡");
        collection.setDrawableId(R.drawable.knowledge_world_africa);

        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "葉門(Yemen)",
                R.drawable.knowledge_world_yemen,
                "知名代表性咖啡: 摩卡馬塔里(Mocha Mattari)\n" +
                        "\n" +
                        "早期葉門的咖啡主要是從摩卡這個港口出口的，因此後來從這裡出口的咖啡都以摩卡作為咖的名子。\n" +
                        "\n" +
                        "葉門的咖啡中以馬塔里(Mattari)及山納妮(Sanani)兩種較有名氣，其中馬塔里嚐起來較濃郁，有較強的果酸而且有可可的味道，而山納妮則是有著較為平衡的口感及香氣。\n" +
                        "\n" +
                        "葉門所生產的咖啡豆是屬阿拉比卡種，一般來說摩卡豆的體積平均較一般咖啡豆小，看起來和其它顆粒大的咖啡豆很不一樣。不過雖然小顆， 它蘊含鮮明的特色，尤其鮮明的果酸常在混合的咖啡中作為提味的角色，難怪它會被比喻為咖啡中的紅酒，香味與酸味令人特別回味。\n" +
                        "\n" +
                        "摩卡這個名字有好幾種寫法，\"Mocca\", \"Mocha\", \"Moka\", \"Mokha\"... 等等，不過它們代表的意思都是一樣的。摩卡咖啡豆因為有獨特的巧克力味， 在深焙時尤其明顯，所以有一種加入巧克力醬調味的花式咖啡也就以\"摩卡咖啡\"作為它的名子了。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "依索比亞(Ethiopia)",
                R.drawable.knowledge_world_ethiopia,
                "知名代表性咖啡: 摩卡哈拉(Mocha Harra), 耶加雪啡(Yirgacheffe)\n" +
                        "\n" +
                        "依索比亞隔著紅海與葉門相對望，是世界上最早發現阿拉比卡種咖啡樹的地方，其境內幾乎各處都在種植咖啡。\n" +
                        "\n" +
                        "其中東部高地哈拉(Harra)產有著名的哈拉摩卡，具有特別的葡萄酒香與酸，和葉門摩卡相當，屬高品質的咖啡。\n" +
                        "\n" +
                        "南部的西達莫(Sidamo)和吉馬(Djimmah)亦是知名的咖啡產地，所生產的咖啡和哈拉所生產的口感稍有不同，酸味方面較為清爽，帶有核果的香味。\n" +
                        "\n" +
                        "在西達莫中有一個小區域名叫耶加雪啡(Yirgacheffe)，其所產的咖啡有著非常迷人的特色，帶有茉莉花及檸檬的香味，以及似蜂蜜般甜甜的特殊味道，因此全球知名。\n" +
                        "\n" +
                        "為什麼摩卡港是在葉門這個國家，但依索比亞產的咖啡也叫做摩卡呢?\n" +
                        "\n" +
                        "那是因為先前衣索比亞的咖啡也是由葉門的摩卡港出口，因此也是以摩卡來命名咖啡，而再以不同的產地名稱來細分，例如摩卡哈拉，摩卡吉馬等。 所以同是摩卡咖啡，有可能它是來自於葉門，也有可能是來自於依索比亞喔。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "象牙海岸(Cote d'Ivoire / Ivory Coast)",
                R.drawable.knowledge_world_ivory_coast,
                "知名代表性咖啡: 無\n" +
                        "\n" +
                        "象牙海岸是西非的一個國家，在幾內亞和賴比瑞亞旁邊。象牙海岸是法語意譯而來的名子，若由發音直譯則為科特迪瓦。\n" +
                        "\n" +
                        "當年在法國統治下，為刺激出口，咖啡隨同可可和棕櫚被大量地種植在海岸地帶，到了1960年代咖啡的產量已增至使 象牙海岸成為僅次於巴西及哥倫比亞的第三大咖啡出口國。\n" +
                        "\n" +
                        "不過近年來由於氣候、經濟及咖啡樹老化減產等影響，其咖啡產量已不 復往日榮景。\n" +
                        "\n" +
                        "既然象牙海岸也是非洲咖啡的主要生產國，為什麼我們對象牙海岸的咖啡並不怎麼耳熟能詳呢?\n" +
                        "\n" +
                        "這是因為象牙海岸的咖啡幾乎全都是羅布斯塔種，僅有少數實驗性質的阿拉比卡種咖啡。\n" +
                        "\n" +
                        "而如前所述，羅布斯塔種的咖啡由於其特殊的味道及特性，大多使用於調合的即溶咖啡或罐裝咖啡等用途，很少以精品咖啡的形式單品直接飲用，因此就不常出現在我們常提的咖啡生產國家之列了。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "肯亞(Kenya)",
                R.drawable.knowledge_world_kenya,
                "知名代表性咖啡: 肯亞AA\n" +
                        "\n" +
                        "肯亞位於東非，恰好位於赤道上，東邊就是印度洋，北邊是依索比亞，南邊則接著坦尚尼亞。\n" +
                        "\n" +
                        "肯亞咖啡的特色是帶有明顯的水果香和果酸，濃郁的口感中還有一點點酒香。\n" +
                        "\n" +
                        "肯亞咖啡多栽種於西南部及東部的高原區， 品種都是阿拉比卡種，且都是水洗咖啡，常見的有波旁（Bourbon）、提比加（Typica）、肯特（Kents）、盧里11（Riuri 11）等四個品種。\n" +
                        "\n" +
                        "肯亞咖啡在淺焙時明亮的果香及果酸有人評為像水果茶，可見其獨特的風味。其中知名的肯亞AA顆粒飽滿，口感豐富濃郁，頗受世人的好評。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "坦尚尼亞(Tanzania)",
                R.drawable.knowledge_world_tanzania,
                "知名代表性咖啡: 克里曼佳羅(Kilimanjaro)\n" +
                        "\n" +
                        "坦尚尼亞也位於非洲東部，就在肯亞的南邊，東邊緊臨印度洋，海岸線長達1500公里之遠。\n" +
                        "\n" +
                        "咖啡在坦尚尼亞來說並非最主要的 農業，大多是小規模經營，其餘尚出口玉米棉花等等數樣大宗農產品。\n" +
                        "\n" +
                        "咖啡多種植在坦尚尼亞北邊鄰近肯亞的克里曼佳羅(亦譯 吉力馬札羅)火山山坡，約有七成是阿拉比卡種，採水洗法處理，而其餘三成的羅布斯塔種則採日曬法處理。\n" +
                        "\n" +
                        "這裡所產的克里曼佳羅 咖啡一般來說有較強的酸性，但較肯亞的稍弱些，口感則與肯亞類似，質感濃郁且帶濃厚的甘甜香氣。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "辛巴威(Zimbabwe)",
                R.drawable.knowledge_world_zimbabwe,
                "知名代表性咖啡: 辛巴威\n" +
                        "\n" +
                        "由左邊的地圖上可以看到，辛巴威位於非洲南部，是個不臨海的內陸國家，右接莫三比克。辛巴威於二十世紀初才開始種植咖啡，主要種植在東部的高原區，皆以水洗法處理。\n" +
                        "\n" +
                        "辛巴威的咖啡特色是和肯亞類似有較強的酸度及水果的香味，但與肯亞不同的 是它帶有一種類似胡椒的味道。 最好的咖啡產自奇平(Chipinge)這個地方。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "馬拉威(Malawi)",
                R.drawable.knowledge_world_malawi,
                "知名代表性咖啡: 馬拉威\n" +
                        "\n" +
                        "馬拉威是非洲東南部一個面積不大的國家，也是個不臨海的內陸國家，其咖啡多栽種在北部高原區，但產量並不大。\n" +
                        "\n" +
                        "不過雖然不臨海，由地圖上可看到在它的東北邊有一個非常大的湖，那是馬拉威湖，也是馬拉威與鄰國分隔的天然疆界。\n" +
                        "\n" +
                        "比起肯亞而言，馬拉威的咖啡也有相當的甘甜度和香氣，而在酸味的表現上則屬較低沈另一種風格。若以高音來比喻肯亞的酸， 馬拉威的酸就像中低音一樣的感覺。\n" +
                        "\n" +
                        "馬拉威栽種的咖啡都是阿拉比卡種，採水洗法處理，是該國的第九大出口產品，僅佔約5% 的出口總額。\n" +
                        "\n" +
                        "不過特別的是，雖然出口數量很有限，但其咖啡的產銷方式是由當地人士自組產銷公司自行負責生產加工出售 等作業，而不是如肯亞由政府主導進行咖啡的集散作業。\n" +
                        "\n" +
                        "在這種獨立操作方式下，馬拉威的咖啡品質一直維持著相當的水準，故也是貿易商 相當喜愛的咖啡之一。"
        ));


        addCoffeeKnowledgeCollection(collection);
    }

    private void centralAmericaCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("中美洲單品咖啡");
        collection.setDrawableId(R.drawable.knowledge_world_central_america);
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "瓜地馬拉(Guatemala)",
                R.drawable.knowledge_world_guatemala,
                "知名代表性咖啡: 安堤瓜(Antigua), 薇薇特楠果(Huehuetenango)\n" +
                        "\n" +
                        "瓜地馬拉的緯度在15度左右，左鄰太平洋，右為加勒比海。靠太平洋這一邊的Sierra山脈是瓜地馬拉咖啡的主要種植區。\n" +
                        "\n" +
                        "因為山脈綿延甚長，地區性氣候變化很大，因此造就了瓜國的七大咖啡產區，各有不同的風味及特色。\n" +
                        "\n" +
                        "其中以安堤瓜這個產區的咖啡因為微酸，香濃甘醇，略帶火山的碳燒味的特色使得瓜地馬拉的咖啡聞名於世界。\n" +
                        "\n" +
                        "瓜國的咖啡屬阿拉比卡種，以水洗法處理， 在較遠的西北方有一片薇薇特楠果(Huehuetenango)高地，因為海拔高於5000英呎，所產咖啡具有高海拔咖啡質地堅硬， 酸性較強且具豐富滋味的特色，亦是瓜地馬拉極有特色的咖啡之一。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "哥斯大黎加(Costa Rica)",
                R.drawable.knowledge_world_costa_rica,
                "知名代表性咖啡: 塔拉蘇(Tarrazu)\n" +
                        "\n" +
                        "哥斯大黎加如同其它中美洲國家一樣，普遍種植的是阿拉比卡種的咖啡。\n" +
                        "\n" +
                        "其咖啡主要栽種在兩個高地區，一是首都聖荷西(San Jose)附近的高地區，另一個則是聖荷西東南方的塔拉蘇(Tarrazu)山區。\n" +
                        "\n" +
                        "因為也是種植在高海拔的關係，哥斯大黎加的咖啡也具有較強的酸味，且因山區溫度較低咖啡樹生長較慢，帶有較複雜而不單調的滋味。\n" +
                        "\n" +
                        "塔拉蘇這個地區生產的咖啡則因為帶有水果味及一些巧克力味或核果味的特殊風味，也是咖啡品嚐者非常喜愛的咖啡之一。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "尼加拉瓜(Nicaragua)",
                R.drawable.knowledge_world_nicaragua,
                "知名代表性咖啡: 尼加拉瓜\n" +
                        "\n" +
                        "尼加拉瓜的咖啡主產於其中部和北部，遮蔭栽種為其特色，咖啡豆則以水洗處理，日曬烘乾。一般而言，尼加拉瓜的咖啡較沒有極度鮮明的特色，因此一般的尼加拉瓜豆常被用來作綜合咖啡或即溶咖啡。\n" +
                        "\n" +
                        "不過高品質的尼加拉瓜豆例如瑪拉果吉佩(Maragogipe, 一種咖啡豆十分巨大稱為象豆的樹種)則評價很高，因為它口味較為均衡，沒有其它中美洲咖啡常帶有的鮮明果酸，但有著清澈的口感以及絕佳的香醇度。 這種圓潤而柔和的質感，也使得它在瓜地馬拉咖啡一般較中庸的評價中一支獨秀，受到許多人的喜愛。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "宏都拉斯(Honduras)",
                R.drawable.knowledge_world_honduras,
                "知名代表性咖啡: 宏都拉斯\n" +
                        "\n" +
                        "宏都拉斯的咖啡在許多喝咖啡的人耳中似乎有些陌生。對於生產咖啡，宏都拉斯的地理條件並不亞於其鄰近的咖啡生產國如瓜地馬拉及尼加拉瓜等。\n" +
                        "\n" +
                        "但先前宏都拉斯由於在生豆的處理及運輸上沒有強而有力的支持，因此在消費市場上的知名度較低。\n" +
                        "\n" +
                        "不過近年來宏國開始極積改變了，對於咖啡 產業的重視使得宏都拉斯的咖啡在國際上慢慢打開了知名度。\n" +
                        "\n" +
                        "宏都拉斯的主要咖啡產區有六個，包含西區互相緊臨的五個區域Santa Barbara, Copan, Ocotepeque, Lempira, La Paz 以及在東南區的 El Paraiso。\n" +
                        "\n" +
                        "宏都拉斯的咖啡口味上酸性較弱，而焦糖的甘甜味較重。來自這五個不同產區的咖啡其滋味也略有不同，有的酸味稍強， 有的有獨特的香味。其品質目前並不差，但因為尚在推展知名度，所以宏國的咖啡價格目前其實頗具競爭力。\n" +
                        "\n" +
                        "而因為其風味上的特性，作單品或加入綜合咖啡的，甚或加入為Espresso成份豆之一都是不錯的選擇。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "巴拿馬(Panama)",
                R.drawable.knowledge_world_panama,
                "知名代表性咖啡: 巴拿馬\n" +
                        "\n" +
                        "巴拿馬西鄰哥斯大黎加，東接南美洲的哥倫比亞，其咖啡多種植於靠西側鄰近哥斯大黎加的山區。\n" +
                        "\n" +
                        "巴拿馬的咖啡風味及質感中等而均勻， 頗有類似藍山的氣質，也因此有此一說巴拿馬的咖啡常被拿來仿為高價的夏威夷可娜或是牙買加藍山。\n" +
                        "\n" +
                        "不過巴拿馬的咖啡在消費市場中一直屬於 中低價的層級，可能是沒有鮮明特色的關係。\n" +
                        "\n" +
                        "但近年來巴拿馬也積極推動精品咖啡的事業，亦曾在競賽拍賣會上高價賣出其優質的咖啡豆，因而巴拿馬的咖啡已逐漸再度受到世人的重視。\n" +
                        "\n" +
                        "好的巴拿馬的豆以乾淨澄澈，明亮溫順的口感，中等的醇度表現令人驚豔，是咖啡品嚐家認為是非常優秀的咖啡之一。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "薩爾瓦多(El Salvador)",
                R.drawable.knowledge_world_el_salvado,
                "知名代表性咖啡: 薩爾瓦多\n" +
                        "\n" +
                        "薩爾瓦多的地勢屬於高地地形，是在中美洲裡面積最小的國家，兩側接著瓜地馬拉及宏都拉斯。\n" +
                        "\n" +
                        "因為境內有兩座平行的高山 ，火山土壤裡有著豐富的礦物質，這種特別的地理環境使得薩爾瓦多具備適合栽種咖啡的條件。\n" +
                        "\n" +
                        "薩爾瓦多的咖啡樹為阿拉比卡種，主要有Bourbon 及 Pacas兩個種別，其咖啡有清爽均勻的特色。\n" +
                        "\n" +
                        "依海拔高低分為三個等級，分別為SHG(Strictly High Grown), SG(High Grown)及SC(Central Standard)等。 值得一提的是，薩爾瓦多出產一款溫泉咖啡，因為使用溫泉水作水洗處理而得名，其特別而柔順的酸味及甘甜香十分迷人，因為產量少使得單價頗高， 是其十分具有特色的咖啡。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "墨西哥(Mexico)",
                R.drawable.knowledge_world_mexico,
                "知名代表性咖啡: 墨西哥\n" +
                        "\n" +
                        "墨西哥北邊緊臨美國，其咖啡產量高居世界第四，主要產地在東南部鄰接瓜地馬拉薇薇特楠果高地的地區。\n" +
                        "\n" +
                        "墨四哥約有七成的咖啡直接輸出至美國， 皆以水洗法處理，亦以高度分為三個等級。一般說來墨西哥咖啡評價是適中的香氣及較淡雅的醇度。\n" +
                        "\n" +
                        "其中巨型象豆樹種 瑪拉果吉佩(Maragogipe，亦作Maragogype)所生產的咖啡豆不僅顆粒比一般大三倍，也以香醇圓潤的口感而聞名。"
        ));

        addCoffeeKnowledgeCollection(collection);
    }

    private void southAmericaCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("南美洲單品咖啡");
        collection.setDrawableId(R.drawable.knowledge_world_south_america);
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "巴西(Brazil)",
                R.drawable.knowledge_world_brazil,
                "知名代表性咖啡: 山多士(Santos)\n" +
                        "\n" +
                        "巴西為世界最大咖啡產國，總產量世界第一，約佔全球總產量的 1/3，主要產地集中於中部及南部的省份。\n" +
                        "\n" +
                        "巴西適合栽咖啡的區域，地勢較平坦，咖啡園多數離海拔1200公尺以下，亦無大樹遮蔭，因採收時生熟漿果同時採下，不算是精品咖啡。\n" +
                        "\n" +
                        "巴西的咖啡品質平均但較少極優的等級，其豆質較軟，烘焙過程中明顯不耐火候， 各品種中以Santos較著名，是以其出口港山多士為名。\n" +
                        "\n" +
                        "巴西咖啡豆性屬中性，可單品來品嚐(雖較單調一點)， 或和其他種類的咖啡豆相混成綜合咖啡，一般被認為是混合調配時不可缺少的咖啡豆。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "哥倫比亞(Colombia)",
                R.drawable.knowledge_world_colombia,
                "知名代表性咖啡: 哥倫比亞\n" +
                        "\n" +
                        "哥倫比亞為世界第二大咖啡輸出國，佔全球產量的約 15%，其咖啡樹多種植於縱貫南北的三座山脈中，僅有阿拉比卡種。\n" +
                        "\n" +
                        "它的產量排名雖低於巴西，不過咖啡豆品質優良，香味豐富而獨特，酸中帶甘，適中的苦味，無論是單飲或混合都非常適宜。\n" +
                        "\n" +
                        "哥倫比亞豆與巴西豆同屬調和式咖啡基本豆之最佳選擇，但其風味則較巴西豆更為甘醇些，香氣也更濃厚，除單品品嚐外， 也常用於調合咖啡中，用來增加咖啡的甘味，調合其它咖啡的苦味。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "祕魯(Peru)",
                R.drawable.knowledge_world_peru,
                "知名代表性咖啡: 祕魯\n" +
                        "\n" +
                        "秘魯為南美主要咖啡生產國之一，而咖啡又是秘魯的第一大出口農產品，近年來出口量大幅成長，2006年的出口量更達到其歷史新高。\n" +
                        "\n" +
                        "由地圖上可看到秘魯境內有安第斯山脈平行著海岸貫穿其中。秘魯的咖啡九成以上種植在北部、首都利馬以東的山谷，以及安第斯山脈山坡的森林地區。\n" +
                        "\n" +
                        "祕魯咖啡豆有圓潤的口感、醇度適中不稠不淡、柔和的酸是其一大特點，另略帶核果的味道。隨著出口量漸增，咖啡愛好著逐漸熟悉， 目前有越來越多的人喜愛上秘魯咖啡其香醇的口感以及柔細酸味。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "厄瓜多爾(Ecuador)",
                R.drawable.knowledge_world_ecuador,
                "知名代表性咖啡: 加拉帕哥(Galapagos)\n" +
                        "\n" +
                        "在哥倫比亞與秘魯之間，赤道經過的厄瓜多爾是南美洲中既出產阿拉比卡伯咖啡也出產羅布斯塔咖啡的少數國家之一。\n" +
                        "\n" +
                        "事實上厄瓜多爾就是西班牙語“赤道”的意思。 由於使用老式傳統採收及處理方式的關係，厄瓜多爾的咖啡並未被列在精品咖啡之列，也因此一般較少看到，也較陌生。\n" +
                        "\n" +
                        "厄瓜多爾面對著太平洋，在其西海岸外約900多公里西經90度的赤道附近，有一加拉帕哥群島(Galapagos Island)也是其屬地， 又名科隆島，生產知名的Galapagos咖啡。\n" +
                        "\n" +
                        "為保護自然生態，厄瓜多爾政府把該群島闢為國家公園， 而且禁止使用化肥、殺蟲劑等化學製劑，因此加拉帕戈斯群島的咖啡被公認為是有機的咖啡。\n" +
                        "\n" +
                        "加拉帕戈斯群島的咖啡風味較平衡偏中性，中度醇厚，帶一點明顯但愉悅的酸，有著特殊的香氣。不過因為較少在市面上見到， 談論此咖啡的人並不是很多。"
        ));


        addCoffeeKnowledgeCollection(collection);
    }

    private void oceaniaCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("大洋洲單品咖啡");
        collection.setDrawableId(R.drawable.knowledge_world_oceania);
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "新幾內亞(New Guinea)",
                R.drawable.knowledge_world_new_guinea,
                "知名代表性咖啡: 新幾內亞\n" +
                        "\n" +
                        "新幾內亞位於澳洲北方，也稱為巴布亞新幾內亞。\n" +
                        "\n" +
                        "這個位於太平洋中的世界第二大島種植的是阿拉比卡的咖啡樹，位於中北部的 Hagen山區，以水洗法來處理咖啡豆。\n" +
                        "\n" +
                        "新幾內的咖啡樹和牙買加藍山屬同種，種籽是於1927年由藍山被帶到新幾內亞成功種植。\n" +
                        "\n" +
                        "新幾內亞的咖啡種植高度約在4500~6000英呎的高度，大多是以小塊田地的方式種植，由數以千計的咖啡農組成合作社的型式來經營咖啡的產銷，主要銷往澳洲及美國。\n" +
                        "\n" +
                        "新幾內亞的咖啡嚐起來口感濃郁而均衡，帶有甘甜味及明亮的酸，有類似水果的香味。 其水果香類似葡萄柚另帶一點巧克力味，也有人認為帶有一點核果的味道。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "澳洲(Australia)",
                R.drawable.knowledge_world_australia,
                "知名代表性咖啡: 澳洲咖啡\n" +
                        "\n" +
                        "澳洲大約於西元1900年前後開始種植咖啡，兼有羅布斯塔種及阿拉比卡種，主要在澳洲東部， 大致分布於新南威爾斯(New South Wales)北方，昆士蘭(Queensland)週邊，以及諾福克島(Norfolk Island)這幾個區域。\n" +
                        "\n" +
                        "新南威爾斯位於澳洲東南邊，是雪梨所在的區域；昆士蘭則是在澳洲東北邊，其東北部為有名的Skybury種植區；而諾福克則島是在澳洲本島東邊遠方南太平洋裡的小島，種有約2萬棵阿拉比卡咖啡樹。\n" +
                        "\n" +
                        "澳洲的咖啡豆品質相當不錯，帶有島嶼豆的特性，香醇而帶著溫和的酸，有別於中美洲通常帶著明亮酸的咖啡豆。其香味略帶巧克力味，單品喝或用於調合都不錯。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "夏威夷(Hawaii)",
                R.drawable.knowledge_world_hawaii,
                "知名代表性咖啡: 可娜(Kona)\n" +
                        "\n" +
                        "夏威夷是幾乎人盡皆知的太平洋熱帶島嶼，除風光明媚外，它也生產咖啡。著名的可那(Knoa)咖啡種植在夏威夷本島西南岸火山山坡，是美國唯一生產的咖啡。\n" +
                        "\n" +
                        "由於島嶼地形加上火山土壤，夏威夷的咖啡具有非常特殊的口感，不會太強烈，不會過酸且有香醇的口感，帶有愉悅的葡萄酒香與酸。\n" +
                        "\n" +
                        "Kona咖啡分四級，分別為最高級的Extra Fancy, Fancy, Prime 及 Gr. No.1 等。 但因產量少且生產成本高，在精品咖啡近年來需求日增的情況下，市場上Kona的價格直追牙買加藍山，上好的Kona豆也越來越難買到。"
        ));


        addCoffeeKnowledgeCollection(collection);
    }

    private void caribbeanSeaCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("加勒比海單品咖啡");
        collection.setDrawableId(R.drawable.knowledge_world_caribbean);
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "牙買加(Jamaica)",
                R.drawable.knowledge_world_jamaica,
                "知名代表性咖啡: 藍山(Blue Mountain)\n" +
                        "\n" +
                        "牙買加是位於加勒比海的一個小共和國，它的藍山咖啡以其溫和的口感及低酸度的高水準表現而聲名大噪，目前是世界上最貴而又最受歡迎的咖啡之一。\n" +
                        "\n" +
                        "藍山位置居於南金士頓(Kingston)到北波特瑪莉亞(Port Maria)之間，高約7500英呎。氣候高冷多濕，雨量充足的情況下造就了藍山咖啡這種特殊溫和的口感。\n" +
                        "\n" +
                        "藍山的咖啡豆顆粒大，品質高是世界公認的極品，有No.1, No.2, No.3, 圓豆(Peaberry), 及藍山區(Blue Mountain Triage)等五級，通 常都附上工廠標誌和保證書，然後裝入圓木桶內出口。\n" +
                        "\n" +
                        "因非常受到日本歡迎，日本對藍山咖啡產業做了相當的投資，目前約有九成都輸往日本，市面上很難買到真品藍山。\n" +
                        "\n" +
                        "牙買加的咖啡早期是種植於聖安琪(Saint Andrew) 附近的山丘上，後來才漸漸擴張到 Blue Mountain 地區。\n" +
                        "\n" +
                        "聖安琪區目前仍是牙買加除藍山外的主要咖啡生產區之一， 海拔6000英呎以上，生產的咖啡和藍山類似，品質也相當高，但因不在藍山上，以牙買加高級水洗(Jamaica Prime washed)為名，其滋味濃郁香醇， 酸甘苦均衡圓潤，市場上售價相當高，也是眾所認為的極品之一。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "波多黎各(Puerto Rico)",
                R.drawable.knowledge_world_puerto_rico,
                "知名代表性咖啡: 波多黎各\n" +
                        "\n" +
                        "一般認為，波多黎各的原始咖啡樹種是1723年由法國人帶到馬丁尼克島(Martinique Island, 加勒比海法屬西印度群島中)的， 很快地在1736年傳進波多黎各，由於多山的環境加上氣候土壤適合，後來很快就變成對歐洲的主要出口品。\n" +
                        "\n" +
                        "其中的尤科(Yauco)區 出產的咖啡因為風味特殊，在歐洲極負盛名，在1890年代時甚至被當作其它國家模仿的標準。波多黎各的咖啡樹為阿拉比卡種， 包含波旁(Bourbon)，波多黎各波旁變種(Puerto Rico Bourbon variety)，卡杜拉(Caturra) 以及Limani的品種。\n" +
                        "\n" +
                        "今日尤科仍是 波多黎各咖啡的代表，由於品質佳，產量少加上人工費用高昂，市場上的價格一向居高不下。尤科特選豆被評為 質感醇厚，風味平衡，溫和，有相當的複雜感而不單調，低酸度低苦味近似牙買加藍山，也是加勒比海裡的極品咖啡之一。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "多明尼加(Dominican Republic)",
                R.drawable.knowledge_world_dominican_republic,
                "知名代表性咖啡: 聖多明尼加(Santo Domingo)\n" +
                        "\n" +
                        "明尼加位於中美洲的伊斯帕尼奧拉(Hispaniola)島，就在前述(波多黎各部份)的馬丁尼克島上方。\n" +
                        "\n" +
                        "多明尼加佔有伊斯帕尼奧拉島右半三分之二的面積，另外左邊三分之一則為海地的領土。 其擁有豐富的天然資源，盛產咖啡、可可、柑橘、香蕉與花卉。\n" +
                        "\n" +
                        "近年來咖啡在多國國內農業生產總值中排名第二， 僅次於稻米，係多國重要之經濟作物。2005-2006年其咖啡出口量高達1億6,000萬磅，較之前大幅成長196.2% 多國的咖啡。\n" +
                        "\n" +
                        "多明尼加咖啡和波多黎各及牙買加咖啡一樣都是加勒比海咖啡，有著類似的調性，但名氣沒有其餘二者來得好， 主要是因為咖啡處理的方式而不是咖啡漿果品質的問題。\n" +
                        "\n" +
                        "多明尼加的咖啡有分高地種植及低地種植，口味上也略不相同。 高地種植的較偏酸，但滋味較豐富；低地的則較不酸，口感較平順一些。近年來精品咖啡盛行，一些多明尼加的莊園所生產的 高品質咖啡豆香氣濃郁，口感醇厚，帶有適度明亮的酸味，已和較負盛名的波多黎各豆或牙買加豆相去不遠，也是值得品嚐的咖啡。"
        ));

        addCoffeeKnowledgeCollection(collection);
    }

    private void makeCupOfCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("沖杯咖啡");
        collection.setDrawableId(R.drawable.knowledge_makecoffee);

        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "塞風式(Siphon)",
                R.drawable.knowledge_makecoffee_siphon,
                "塞風式咖啡壺一般也稱為「虹吸式」，也稱為「真空過濾式」，是由上杯、下杯、置杯架、濾布和加熱器等物件所組成，並利用熱漲冷縮的物理原理沖煮咖啡，水溫在酒精燈的加熱下，由下杯上升到上杯裡後，被抽出的咖啡液再度回到下杯裡。\n" +
                        "\n" +
                        "整個過程既穩定又能夠欣賞沖泡的樂趣，故而受到眾多咖啡迷們的喜愛。\n" +
                        "\n" +
                        "用虹吸式咖啡壺來煮咖啡，要注意幾點：水量、水質、火候、咖啡粉的用量和粗細、攪拌、時間等 ，並且塞風式咖啡壺一般都是玻璃製品，因此在沖煮故程中要格外小心以免打破。\n" +
                        "\n" +
                        "使用塞風式咖啡壺沖泡的的咖啡粉以中粗研磨度為佳，以一杯咖啡十公克的咖啡粉量計算，\n" +
                        "\n" +
                        "沖煮步驟及方法如下：\n" +
                        "\n" +
                        "    1. 下杯裝水並進行加熱，加熱裝置選用酒精燈或小瓦斯燈皆可(以下稱熱源)。\n" +
                        "\n" +
                        "    2. 水沸騰後持續加熱一分鐘(讓整個下杯升溫平均)。\n" +
                        "\n" +
                        "    3. 濾布擺放於上杯的正中央位置，並將上杯插至下杯中。\n" +
                        "\n" +
                        "    4. 待下杯內的水因沸騰而上升至上杯後移開熱源，將咖啡粉倒進上杯裡。咖啡粉入水後開始計時。\n" +
                        "\n" +
                        "    5. 移回熱源並持續加熱約30秒後，以攪拌棒攪動數次。約一分鐘至一分半(視咖啡種類及喜好風味決定)時移開熱源熄火。\n" +
                        "    此間若熱源為瓦斯燈者需調為小火力，維持水在上杯即可，勿大火以免過高溫破壞咖啡美味。\n" +
                        "\n" +
                        "    6. 熄火之後，再以攪拌棒以相同轉向輕攪數次，之後迅速以微濕的布蓋住下杯的上部，讓咖啡快速回流到下杯即沖煮完成。\n" +
                        "    下杯上部蓋濕布的用意是加速冷卻增大虹吸力量，儘速讓咖啡汁與咖啡粉分離，避免萃取時間過長影響整體風味。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "義式機器沖煮式(Espresso)",
                R.drawable.knowledge_makecoffee_espresso,
                "咖啡是一種極不穩定的液體，因為義式濃縮咖啡的沖煮步驟十分繁雜，每一個步驟都會影響到義式濃縮咖啡的最終品質，並改變咖啡的特性，所以瞭解其沖煮方式並選擇適當的咖啡豆十分的重要。\n" +
                        "\n" +
                        "義式濃縮咖啡機可以連續萃取數杯咖啡，沖煮過程中的高壓能將咖啡豆中的油脂和膠質乳化溶解，咖啡豆中的菁華經由壓力被完全萃取出來，使得煮出的咖啡濃度更濃，口味和香味更好。\n" +
                        "\n" +
                        "記得不要將咖啡研磨的太細，否則萃取出來的咖啡將會太苦。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "摩卡(Moka)",
                R.drawable.knowledge_makecoffee_moke,
                "為蒸餾式的一種，以摩卡壺所沖煮出的咖啡帶著一種沈穩的濃稠和香味。\n" +
                        "\n" +
                        "摩卡壺分為上下兩部份，水在壺的下半部被煮開至沸騰，水滾時藉著蒸氣的壓力，滾水上升經過裝有咖啡粉的過濾器而至壺的上半部。\n" +
                        "\n" +
                        "當咖啡開始流向壺的上半部時，需將火關小，因為溫度太高會使咖啡產生焦味而破壞了其原始風味。\n" +
                        "\n" +
                        "常見的塞風式煮法也是利用相同的原理。"
        ));
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "過濾式(Filter coffee)",
                R.drawable.knowledge_makecoffee_filter_coffee,
                "這是一種濾紙滴落式的沖泡方式，主要是讓咖啡豆粉與熱水充分混合後，溶解出咖啡中的濃醇甘香，再透過濾紙滴漏出來，此種方式能過濾咖啡中所含的脂肪、蛋白質及不良雜質，得到口感較清爽的咖啡，在享受美味的同時並能得到自己手沖咖啡的樂趣。\n" +
                        "\n" +
                        "水量、注水時間、咖啡粉的研磨度和濾杯的選擇都會影響此種沖煮方式最後的咖啡風味，因此以沖煮一人份為例，共需10g的咖啡粉，需要的水量約為100 c.c.，沖煮步驟及方法如下：\n" +
                        "\n" +
                        "    1. 先以熱水溫熱濾杯，將濾紙擺放於濾杯中央，再倒入咖啡粉。\n" +
                        "\n" +
                        "    2. 輕輕搖晃濾杯，盡量使咖啡粉的表面平整。\n" +
                        "\n" +
                        "    3. 將水壺的注水口提高至距離濾杯表面約3～4公分的高度，並由內而外、沿著順時鐘方向畫圓圈，充分潤濕咖啡粉。注水量的判斷原則以下方承接咖啡之器皿內有「數滴」咖啡滴出為標準。\n" +
                        "    \n" +
                        "    4. 若使用的是新鮮的咖啡粉，此時其表面會形成一個膨脹的過濾層，這時須注意水流應保持細小不可過強，水流適當與否可以此膨脹過濾層會不會塌陷做為參考。\n" +
                        "    \n" +
                        "    5. 第二次注入熱水，重複步驟3的注水方法，並特別小心不可將熱水倒在先前膨脹的過濾層的外側周圍部分。\n" +
                        "    \n" +
                        "    6. 每次注水的時間點為膨脹的過濾層漸漸凹陷之後，再將剩餘的熱水注入，即完成。"
        ));


        addCoffeeKnowledgeCollection(collection);
    }

    private void buyCoffee() {
        CoffeeKnowledgeCollection collection = new CoffeeKnowledgeCollection();
        collection.setName("");
        collection.setDrawableId(R.drawable.knowledge_coffee_bean);
        collection.addCoffeeKnowledge(new CoffeeKnowledge(
                "",
                R.drawable.knowledge_coffee_bean_01,
                ""
        ));


        addCoffeeKnowledgeCollection(collection);
    }


}
