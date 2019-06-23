package com.qunar.im.ui.view.emojiconTextView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.Spannable;
import android.util.SparseIntArray;
import com.qunar.im.base.util.EmotionUtils;
import com.qunar.im.base.util.graphics.BitmapHelper;
import com.qunar.im.base.view.faceGridView.EmoticonEntity;
import com.qunar.im.common.R;
import java.io.IOException;
import java.io.InputStream;

public final class EmojiconHandler {
    private static final SparseIntArray sEmojisMap = new SparseIntArray(846);
    private static final SparseIntArray sSoftbanksMap = new SparseIntArray(471);

    private EmojiconHandler() {
    }

    static {
        sEmojisMap.put(128516, R.drawable.atom_ui_emoji_1f604);
        sEmojisMap.put(128515, R.drawable.atom_ui_emoji_1f603);
        sEmojisMap.put(128512, R.drawable.atom_ui_emoji_1f600);
        sEmojisMap.put(128522, R.drawable.atom_ui_emoji_1f60a);
        sEmojisMap.put(9786, R.drawable.atom_ui_emoji_263a);
        sEmojisMap.put(128521, R.drawable.atom_ui_emoji_1f609);
        sEmojisMap.put(128525, R.drawable.atom_ui_emoji_1f60d);
        sEmojisMap.put(128536, R.drawable.atom_ui_emoji_1f618);
        sEmojisMap.put(128538, R.drawable.atom_ui_emoji_1f61a);
        sEmojisMap.put(128535, R.drawable.atom_ui_emoji_1f617);
        sEmojisMap.put(128537, R.drawable.atom_ui_emoji_1f619);
        sEmojisMap.put(128540, R.drawable.atom_ui_emoji_1f61c);
        sEmojisMap.put(128541, R.drawable.atom_ui_emoji_1f61d);
        sEmojisMap.put(128539, R.drawable.atom_ui_emoji_1f61b);
        sEmojisMap.put(128563, R.drawable.atom_ui_emoji_1f633);
        sEmojisMap.put(128513, R.drawable.atom_ui_emoji_1f601);
        sEmojisMap.put(128532, R.drawable.atom_ui_emoji_1f614);
        sEmojisMap.put(128524, R.drawable.atom_ui_emoji_1f60c);
        sEmojisMap.put(128530, R.drawable.atom_ui_emoji_1f612);
        sEmojisMap.put(128542, R.drawable.atom_ui_emoji_1f61e);
        sEmojisMap.put(128547, R.drawable.atom_ui_emoji_1f623);
        sEmojisMap.put(128546, R.drawable.atom_ui_emoji_1f622);
        sEmojisMap.put(128514, R.drawable.atom_ui_emoji_1f602);
        sEmojisMap.put(128557, R.drawable.atom_ui_emoji_1f62d);
        sEmojisMap.put(128554, R.drawable.atom_ui_emoji_1f62a);
        sEmojisMap.put(128549, R.drawable.atom_ui_emoji_1f625);
        sEmojisMap.put(128560, R.drawable.atom_ui_emoji_1f630);
        sEmojisMap.put(128517, R.drawable.atom_ui_emoji_1f605);
        sEmojisMap.put(128531, R.drawable.atom_ui_emoji_1f613);
        sEmojisMap.put(128553, R.drawable.atom_ui_emoji_1f629);
        sEmojisMap.put(128555, R.drawable.atom_ui_emoji_1f62b);
        sEmojisMap.put(128552, R.drawable.atom_ui_emoji_1f628);
        sEmojisMap.put(128561, R.drawable.atom_ui_emoji_1f631);
        sEmojisMap.put(128544, R.drawable.atom_ui_emoji_1f620);
        sEmojisMap.put(128545, R.drawable.atom_ui_emoji_1f621);
        sEmojisMap.put(128548, R.drawable.atom_ui_emoji_1f624);
        sEmojisMap.put(128534, R.drawable.atom_ui_emoji_1f616);
        sEmojisMap.put(128518, R.drawable.atom_ui_emoji_1f606);
        sEmojisMap.put(128523, R.drawable.atom_ui_emoji_1f60b);
        sEmojisMap.put(128567, R.drawable.atom_ui_emoji_1f637);
        sEmojisMap.put(128526, R.drawable.atom_ui_emoji_1f60e);
        sEmojisMap.put(128564, R.drawable.atom_ui_emoji_1f634);
        sEmojisMap.put(128565, R.drawable.atom_ui_emoji_1f635);
        sEmojisMap.put(128562, R.drawable.atom_ui_emoji_1f632);
        sEmojisMap.put(128543, R.drawable.atom_ui_emoji_1f61f);
        sEmojisMap.put(128550, R.drawable.atom_ui_emoji_1f626);
        sEmojisMap.put(128551, R.drawable.atom_ui_emoji_1f627);
        sEmojisMap.put(128520, R.drawable.atom_ui_emoji_1f608);
        sEmojisMap.put(128127, R.drawable.atom_ui_emoji_1f47f);
        sEmojisMap.put(128558, R.drawable.atom_ui_emoji_1f62e);
        sEmojisMap.put(128556, R.drawable.atom_ui_emoji_1f62c);
        sEmojisMap.put(128528, R.drawable.atom_ui_emoji_1f610);
        sEmojisMap.put(128533, R.drawable.atom_ui_emoji_1f615);
        sEmojisMap.put(128559, R.drawable.atom_ui_emoji_1f62f);
        sEmojisMap.put(128566, R.drawable.atom_ui_emoji_1f636);
        sEmojisMap.put(128519, R.drawable.atom_ui_emoji_1f607);
        sEmojisMap.put(128527, R.drawable.atom_ui_emoji_1f60f);
        sEmojisMap.put(128529, R.drawable.atom_ui_emoji_1f611);
        sEmojisMap.put(128114, R.drawable.atom_ui_emoji_1f472);
        sEmojisMap.put(128115, R.drawable.atom_ui_emoji_1f473);
        sEmojisMap.put(128110, R.drawable.atom_ui_emoji_1f46e);
        sEmojisMap.put(128119, R.drawable.atom_ui_emoji_1f477);
        sEmojisMap.put(128130, R.drawable.atom_ui_emoji_1f482);
        sEmojisMap.put(128118, R.drawable.atom_ui_emoji_1f476);
        sEmojisMap.put(128102, R.drawable.atom_ui_emoji_1f466);
        sEmojisMap.put(128103, R.drawable.atom_ui_emoji_1f467);
        sEmojisMap.put(128104, R.drawable.atom_ui_emoji_1f468);
        sEmojisMap.put(128105, R.drawable.atom_ui_emoji_1f469);
        sEmojisMap.put(128116, R.drawable.atom_ui_emoji_1f474);
        sEmojisMap.put(128117, R.drawable.atom_ui_emoji_1f475);
        sEmojisMap.put(128113, R.drawable.atom_ui_emoji_1f471);
        sEmojisMap.put(128124, R.drawable.atom_ui_emoji_1f47c);
        sEmojisMap.put(128120, R.drawable.atom_ui_emoji_1f478);
        sEmojisMap.put(128570, R.drawable.atom_ui_emoji_1f63a);
        sEmojisMap.put(128568, R.drawable.atom_ui_emoji_1f638);
        sEmojisMap.put(128571, R.drawable.atom_ui_emoji_1f63b);
        sEmojisMap.put(128573, R.drawable.atom_ui_emoji_1f63d);
        sEmojisMap.put(128572, R.drawable.atom_ui_emoji_1f63c);
        sEmojisMap.put(128576, R.drawable.atom_ui_emoji_1f640);
        sEmojisMap.put(128575, R.drawable.atom_ui_emoji_1f63f);
        sEmojisMap.put(128569, R.drawable.atom_ui_emoji_1f639);
        sEmojisMap.put(128574, R.drawable.atom_ui_emoji_1f63e);
        sEmojisMap.put(128121, R.drawable.atom_ui_emoji_1f479);
        sEmojisMap.put(128122, R.drawable.atom_ui_emoji_1f47a);
        sEmojisMap.put(128584, R.drawable.atom_ui_emoji_1f648);
        sEmojisMap.put(128585, R.drawable.atom_ui_emoji_1f649);
        sEmojisMap.put(128586, R.drawable.atom_ui_emoji_1f64a);
        sEmojisMap.put(128128, R.drawable.atom_ui_emoji_1f480);
        sEmojisMap.put(128125, R.drawable.atom_ui_emoji_1f47d);
        sEmojisMap.put(128169, R.drawable.atom_ui_emoji_1f4a9);
        sEmojisMap.put(128293, R.drawable.atom_ui_emoji_1f525);
        sEmojisMap.put(10024, R.drawable.atom_ui_emoji_2728);
        sEmojisMap.put(127775, R.drawable.atom_ui_emoji_1f31f);
        sEmojisMap.put(128171, R.drawable.atom_ui_emoji_1f4ab);
        sEmojisMap.put(128165, R.drawable.atom_ui_emoji_1f4a5);
        sEmojisMap.put(128162, R.drawable.atom_ui_emoji_1f4a2);
        sEmojisMap.put(128166, R.drawable.atom_ui_emoji_1f4a6);
        sEmojisMap.put(128167, R.drawable.atom_ui_emoji_1f4a7);
        sEmojisMap.put(128164, R.drawable.atom_ui_emoji_1f4a4);
        sEmojisMap.put(128168, R.drawable.atom_ui_emoji_1f4a8);
        sEmojisMap.put(128066, R.drawable.atom_ui_emoji_1f442);
        sEmojisMap.put(128064, R.drawable.atom_ui_emoji_1f440);
        sEmojisMap.put(128067, R.drawable.atom_ui_emoji_1f443);
        sEmojisMap.put(128069, R.drawable.atom_ui_emoji_1f445);
        sEmojisMap.put(128068, R.drawable.atom_ui_emoji_1f444);
        sEmojisMap.put(128077, R.drawable.atom_ui_emoji_1f44d);
        sEmojisMap.put(128078, R.drawable.atom_ui_emoji_1f44e);
        sEmojisMap.put(128076, R.drawable.atom_ui_emoji_1f44c);
        sEmojisMap.put(128074, R.drawable.atom_ui_emoji_1f44a);
        sEmojisMap.put(9994, R.drawable.atom_ui_emoji_270a);
        sEmojisMap.put(9996, R.drawable.atom_ui_emoji_270c);
        sEmojisMap.put(128075, R.drawable.atom_ui_emoji_1f44b);
        sEmojisMap.put(9995, R.drawable.atom_ui_emoji_270b);
        sEmojisMap.put(128080, R.drawable.atom_ui_emoji_1f450);
        sEmojisMap.put(128070, R.drawable.atom_ui_emoji_1f446);
        sEmojisMap.put(128071, R.drawable.atom_ui_emoji_1f447);
        sEmojisMap.put(128073, R.drawable.atom_ui_emoji_1f449);
        sEmojisMap.put(128072, R.drawable.atom_ui_emoji_1f448);
        sEmojisMap.put(128588, R.drawable.atom_ui_emoji_1f64c);
        sEmojisMap.put(128591, R.drawable.atom_ui_emoji_1f64f);
        sEmojisMap.put(9757, R.drawable.atom_ui_emoji_261d);
        sEmojisMap.put(128079, R.drawable.atom_ui_emoji_1f44f);
        sEmojisMap.put(128170, R.drawable.atom_ui_emoji_1f4aa);
        sEmojisMap.put(128694, R.drawable.atom_ui_emoji_1f6b6);
        sEmojisMap.put(127939, R.drawable.atom_ui_emoji_1f3c3);
        sEmojisMap.put(128131, R.drawable.atom_ui_emoji_1f483);
        sEmojisMap.put(128107, R.drawable.atom_ui_emoji_1f46b);
        sEmojisMap.put(128106, R.drawable.atom_ui_emoji_1f46a);
        sEmojisMap.put(128108, R.drawable.atom_ui_emoji_1f46c);
        sEmojisMap.put(128109, R.drawable.atom_ui_emoji_1f46d);
        sEmojisMap.put(128143, R.drawable.atom_ui_emoji_1f48f);
        sEmojisMap.put(128145, R.drawable.atom_ui_emoji_1f491);
        sEmojisMap.put(128111, R.drawable.atom_ui_emoji_1f46f);
        sEmojisMap.put(128582, R.drawable.atom_ui_emoji_1f646);
        sEmojisMap.put(128581, R.drawable.atom_ui_emoji_1f645);
        sEmojisMap.put(128129, R.drawable.atom_ui_emoji_1f481);
        sEmojisMap.put(128587, R.drawable.atom_ui_emoji_1f64b);
        sEmojisMap.put(128134, R.drawable.atom_ui_emoji_1f486);
        sEmojisMap.put(128135, R.drawable.atom_ui_emoji_1f487);
        sEmojisMap.put(128133, R.drawable.atom_ui_emoji_1f485);
        sEmojisMap.put(128112, R.drawable.atom_ui_emoji_1f470);
        sEmojisMap.put(128590, R.drawable.atom_ui_emoji_1f64e);
        sEmojisMap.put(128589, R.drawable.atom_ui_emoji_1f64d);
        sEmojisMap.put(128583, R.drawable.atom_ui_emoji_1f647);
        sEmojisMap.put(127913, R.drawable.atom_ui_emoji_1f3a9);
        sEmojisMap.put(128081, R.drawable.atom_ui_emoji_1f451);
        sEmojisMap.put(128082, R.drawable.atom_ui_emoji_1f452);
        sEmojisMap.put(128095, R.drawable.atom_ui_emoji_1f45f);
        sEmojisMap.put(128094, R.drawable.atom_ui_emoji_1f45e);
        sEmojisMap.put(128097, R.drawable.atom_ui_emoji_1f461);
        sEmojisMap.put(128096, R.drawable.atom_ui_emoji_1f460);
        sEmojisMap.put(128098, R.drawable.atom_ui_emoji_1f462);
        sEmojisMap.put(128085, R.drawable.atom_ui_emoji_1f455);
        sEmojisMap.put(128084, R.drawable.atom_ui_emoji_1f454);
        sEmojisMap.put(128090, R.drawable.atom_ui_emoji_1f45a);
        sEmojisMap.put(128087, R.drawable.atom_ui_emoji_1f457);
        sEmojisMap.put(127933, R.drawable.atom_ui_emoji_1f3bd);
        sEmojisMap.put(128086, R.drawable.atom_ui_emoji_1f456);
        sEmojisMap.put(128088, R.drawable.atom_ui_emoji_1f458);
        sEmojisMap.put(128089, R.drawable.atom_ui_emoji_1f459);
        sEmojisMap.put(128188, R.drawable.atom_ui_emoji_1f4bc);
        sEmojisMap.put(128092, R.drawable.atom_ui_emoji_1f45c);
        sEmojisMap.put(128093, R.drawable.atom_ui_emoji_1f45d);
        sEmojisMap.put(128091, R.drawable.atom_ui_emoji_1f45b);
        sEmojisMap.put(128083, R.drawable.atom_ui_emoji_1f453);
        sEmojisMap.put(127872, R.drawable.atom_ui_emoji_1f380);
        sEmojisMap.put(127746, R.drawable.atom_ui_emoji_1f302);
        sEmojisMap.put(128132, R.drawable.atom_ui_emoji_1f484);
        sEmojisMap.put(128155, R.drawable.atom_ui_emoji_1f49b);
        sEmojisMap.put(128153, R.drawable.atom_ui_emoji_1f499);
        sEmojisMap.put(128156, R.drawable.atom_ui_emoji_1f49c);
        sEmojisMap.put(128154, R.drawable.atom_ui_emoji_1f49a);
        sEmojisMap.put(10084, R.drawable.atom_ui_emoji_2764);
        sEmojisMap.put(128148, R.drawable.atom_ui_emoji_1f494);
        sEmojisMap.put(128151, R.drawable.atom_ui_emoji_1f497);
        sEmojisMap.put(128147, R.drawable.atom_ui_emoji_1f493);
        sEmojisMap.put(128149, R.drawable.atom_ui_emoji_1f495);
        sEmojisMap.put(128150, R.drawable.atom_ui_emoji_1f496);
        sEmojisMap.put(128158, R.drawable.atom_ui_emoji_1f49e);
        sEmojisMap.put(128152, R.drawable.atom_ui_emoji_1f498);
        sEmojisMap.put(128140, R.drawable.atom_ui_emoji_1f48c);
        sEmojisMap.put(128139, R.drawable.atom_ui_emoji_1f48b);
        sEmojisMap.put(128141, R.drawable.atom_ui_emoji_1f48d);
        sEmojisMap.put(128142, R.drawable.atom_ui_emoji_1f48e);
        sEmojisMap.put(128100, R.drawable.atom_ui_emoji_1f464);
        sEmojisMap.put(128101, R.drawable.atom_ui_emoji_1f465);
        sEmojisMap.put(128172, R.drawable.atom_ui_emoji_1f4ac);
        sEmojisMap.put(128099, R.drawable.atom_ui_emoji_1f463);
        sEmojisMap.put(128173, R.drawable.atom_ui_emoji_1f4ad);
        sEmojisMap.put(128054, R.drawable.atom_ui_emoji_1f436);
        sEmojisMap.put(128058, R.drawable.atom_ui_emoji_1f43a);
        sEmojisMap.put(128049, R.drawable.atom_ui_emoji_1f431);
        sEmojisMap.put(128045, R.drawable.atom_ui_emoji_1f42d);
        sEmojisMap.put(128057, R.drawable.atom_ui_emoji_1f439);
        sEmojisMap.put(128048, R.drawable.atom_ui_emoji_1f430);
        sEmojisMap.put(128056, R.drawable.atom_ui_emoji_1f438);
        sEmojisMap.put(128047, R.drawable.atom_ui_emoji_1f42f);
        sEmojisMap.put(128040, R.drawable.atom_ui_emoji_1f428);
        sEmojisMap.put(128059, R.drawable.atom_ui_emoji_1f43b);
        sEmojisMap.put(128055, R.drawable.atom_ui_emoji_1f437);
        sEmojisMap.put(128061, R.drawable.atom_ui_emoji_1f43d);
        sEmojisMap.put(128046, R.drawable.atom_ui_emoji_1f42e);
        sEmojisMap.put(128023, R.drawable.atom_ui_emoji_1f417);
        sEmojisMap.put(128053, R.drawable.atom_ui_emoji_1f435);
        sEmojisMap.put(128018, R.drawable.atom_ui_emoji_1f412);
        sEmojisMap.put(128052, R.drawable.atom_ui_emoji_1f434);
        sEmojisMap.put(128017, R.drawable.atom_ui_emoji_1f411);
        sEmojisMap.put(128024, R.drawable.atom_ui_emoji_1f418);
        sEmojisMap.put(128060, R.drawable.atom_ui_emoji_1f43c);
        sEmojisMap.put(128039, R.drawable.atom_ui_emoji_1f427);
        sEmojisMap.put(128038, R.drawable.atom_ui_emoji_1f426);
        sEmojisMap.put(128036, R.drawable.atom_ui_emoji_1f424);
        sEmojisMap.put(128037, R.drawable.atom_ui_emoji_1f425);
        sEmojisMap.put(128035, R.drawable.atom_ui_emoji_1f423);
        sEmojisMap.put(128020, R.drawable.atom_ui_emoji_1f414);
        sEmojisMap.put(128013, R.drawable.atom_ui_emoji_1f40d);
        sEmojisMap.put(128034, R.drawable.atom_ui_emoji_1f422);
        sEmojisMap.put(128027, R.drawable.atom_ui_emoji_1f41b);
        sEmojisMap.put(128029, R.drawable.atom_ui_emoji_1f41d);
        sEmojisMap.put(128028, R.drawable.atom_ui_emoji_1f41c);
        sEmojisMap.put(128030, R.drawable.atom_ui_emoji_1f41e);
        sEmojisMap.put(128012, R.drawable.atom_ui_emoji_1f40c);
        sEmojisMap.put(128025, R.drawable.atom_ui_emoji_1f419);
        sEmojisMap.put(128026, R.drawable.atom_ui_emoji_1f41a);
        sEmojisMap.put(128032, R.drawable.atom_ui_emoji_1f420);
        sEmojisMap.put(128031, R.drawable.atom_ui_emoji_1f41f);
        sEmojisMap.put(128044, R.drawable.atom_ui_emoji_1f42c);
        sEmojisMap.put(128051, R.drawable.atom_ui_emoji_1f433);
        sEmojisMap.put(128011, R.drawable.atom_ui_emoji_1f40b);
        sEmojisMap.put(128004, R.drawable.atom_ui_emoji_1f404);
        sEmojisMap.put(128015, R.drawable.atom_ui_emoji_1f40f);
        sEmojisMap.put(128000, R.drawable.atom_ui_emoji_1f400);
        sEmojisMap.put(128003, R.drawable.atom_ui_emoji_1f403);
        sEmojisMap.put(128005, R.drawable.atom_ui_emoji_1f405);
        sEmojisMap.put(128007, R.drawable.atom_ui_emoji_1f407);
        sEmojisMap.put(128009, R.drawable.atom_ui_emoji_1f409);
        sEmojisMap.put(128014, R.drawable.atom_ui_emoji_1f40e);
        sEmojisMap.put(128016, R.drawable.atom_ui_emoji_1f410);
        sEmojisMap.put(128019, R.drawable.atom_ui_emoji_1f413);
        sEmojisMap.put(128021, R.drawable.atom_ui_emoji_1f415);
        sEmojisMap.put(128022, R.drawable.atom_ui_emoji_1f416);
        sEmojisMap.put(128001, R.drawable.atom_ui_emoji_1f401);
        sEmojisMap.put(128002, R.drawable.atom_ui_emoji_1f402);
        sEmojisMap.put(128050, R.drawable.atom_ui_emoji_1f432);
        sEmojisMap.put(128033, R.drawable.atom_ui_emoji_1f421);
        sEmojisMap.put(128010, R.drawable.atom_ui_emoji_1f40a);
        sEmojisMap.put(128043, R.drawable.atom_ui_emoji_1f42b);
        sEmojisMap.put(128042, R.drawable.atom_ui_emoji_1f42a);
        sEmojisMap.put(128006, R.drawable.atom_ui_emoji_1f406);
        sEmojisMap.put(128008, R.drawable.atom_ui_emoji_1f408);
        sEmojisMap.put(128041, R.drawable.atom_ui_emoji_1f429);
        sEmojisMap.put(128062, R.drawable.atom_ui_emoji_1f43e);
        sEmojisMap.put(128144, R.drawable.atom_ui_emoji_1f490);
        sEmojisMap.put(127800, R.drawable.atom_ui_emoji_1f338);
        sEmojisMap.put(127799, R.drawable.atom_ui_emoji_1f337);
        sEmojisMap.put(127808, R.drawable.atom_ui_emoji_1f340);
        sEmojisMap.put(127801, R.drawable.atom_ui_emoji_1f339);
        sEmojisMap.put(127803, R.drawable.atom_ui_emoji_1f33b);
        sEmojisMap.put(127802, R.drawable.atom_ui_emoji_1f33a);
        sEmojisMap.put(127809, R.drawable.atom_ui_emoji_1f341);
        sEmojisMap.put(127811, R.drawable.atom_ui_emoji_1f343);
        sEmojisMap.put(127810, R.drawable.atom_ui_emoji_1f342);
        sEmojisMap.put(127807, R.drawable.atom_ui_emoji_1f33f);
        sEmojisMap.put(127806, R.drawable.atom_ui_emoji_1f33e);
        sEmojisMap.put(127812, R.drawable.atom_ui_emoji_1f344);
        sEmojisMap.put(127797, R.drawable.atom_ui_emoji_1f335);
        sEmojisMap.put(127796, R.drawable.atom_ui_emoji_1f334);
        sEmojisMap.put(127794, R.drawable.atom_ui_emoji_1f332);
        sEmojisMap.put(127795, R.drawable.atom_ui_emoji_1f333);
        sEmojisMap.put(127792, R.drawable.atom_ui_emoji_1f330);
        sEmojisMap.put(127793, R.drawable.atom_ui_emoji_1f331);
        sEmojisMap.put(127804, R.drawable.atom_ui_emoji_1f33c);
        sEmojisMap.put(127760, R.drawable.atom_ui_emoji_1f310);
        sEmojisMap.put(127774, R.drawable.atom_ui_emoji_1f31e);
        sEmojisMap.put(127773, R.drawable.atom_ui_emoji_1f31d);
        sEmojisMap.put(127770, R.drawable.atom_ui_emoji_1f31a);
        sEmojisMap.put(127761, R.drawable.atom_ui_emoji_1f311);
        sEmojisMap.put(127762, R.drawable.atom_ui_emoji_1f312);
        sEmojisMap.put(127763, R.drawable.atom_ui_emoji_1f313);
        sEmojisMap.put(127764, R.drawable.atom_ui_emoji_1f314);
        sEmojisMap.put(127765, R.drawable.atom_ui_emoji_1f315);
        sEmojisMap.put(127766, R.drawable.atom_ui_emoji_1f316);
        sEmojisMap.put(127767, R.drawable.atom_ui_emoji_1f317);
        sEmojisMap.put(127768, R.drawable.atom_ui_emoji_1f318);
        sEmojisMap.put(127772, R.drawable.atom_ui_emoji_1f31c);
        sEmojisMap.put(127771, R.drawable.atom_ui_emoji_1f31b);
        sEmojisMap.put(127769, R.drawable.atom_ui_emoji_1f319);
        sEmojisMap.put(127757, R.drawable.atom_ui_emoji_1f30d);
        sEmojisMap.put(127758, R.drawable.atom_ui_emoji_1f30e);
        sEmojisMap.put(127759, R.drawable.atom_ui_emoji_1f30f);
        sEmojisMap.put(127755, R.drawable.atom_ui_emoji_1f30b);
        sEmojisMap.put(127756, R.drawable.atom_ui_emoji_1f30c);
        sEmojisMap.put(127776, R.drawable.atom_ui_emoji_1f303);
        sEmojisMap.put(11088, R.drawable.atom_ui_emoji_2b50);
        sEmojisMap.put(9728, R.drawable.atom_ui_emoji_2600);
        sEmojisMap.put(9925, R.drawable.atom_ui_emoji_26c5);
        sEmojisMap.put(9729, R.drawable.atom_ui_emoji_2601);
        sEmojisMap.put(9889, R.drawable.atom_ui_emoji_26a1);
        sEmojisMap.put(9748, R.drawable.atom_ui_emoji_2614);
        sEmojisMap.put(10052, R.drawable.atom_ui_emoji_2744);
        sEmojisMap.put(9924, R.drawable.atom_ui_emoji_26c4);
        sEmojisMap.put(127744, R.drawable.atom_ui_emoji_1f300);
        sEmojisMap.put(127745, R.drawable.atom_ui_emoji_1f301);
        sEmojisMap.put(127752, R.drawable.atom_ui_emoji_1f308);
        sEmojisMap.put(127754, R.drawable.atom_ui_emoji_1f30a);
        sEmojisMap.put(127885, R.drawable.atom_ui_emoji_1f38d);
        sEmojisMap.put(128157, R.drawable.atom_ui_emoji_1f49d);
        sEmojisMap.put(127886, R.drawable.atom_ui_emoji_1f38e);
        sEmojisMap.put(127890, R.drawable.atom_ui_emoji_1f392);
        sEmojisMap.put(127891, R.drawable.atom_ui_emoji_1f393);
        sEmojisMap.put(127887, R.drawable.atom_ui_emoji_1f38f);
        sEmojisMap.put(127878, R.drawable.atom_ui_emoji_1f386);
        sEmojisMap.put(127879, R.drawable.atom_ui_emoji_1f387);
        sEmojisMap.put(127888, R.drawable.atom_ui_emoji_1f390);
        sEmojisMap.put(127889, R.drawable.atom_ui_emoji_1f391);
        sEmojisMap.put(127875, R.drawable.atom_ui_emoji_1f383);
        sEmojisMap.put(128123, R.drawable.atom_ui_emoji_1f47b);
        sEmojisMap.put(127877, R.drawable.atom_ui_emoji_1f385);
        sEmojisMap.put(127876, R.drawable.atom_ui_emoji_1f384);
        sEmojisMap.put(127873, R.drawable.atom_ui_emoji_1f381);
        sEmojisMap.put(127883, R.drawable.atom_ui_emoji_1f38b);
        sEmojisMap.put(127881, R.drawable.atom_ui_emoji_1f389);
        sEmojisMap.put(127882, R.drawable.atom_ui_emoji_1f38a);
        sEmojisMap.put(127880, R.drawable.atom_ui_emoji_1f388);
        sEmojisMap.put(127884, R.drawable.atom_ui_emoji_1f38c);
        sEmojisMap.put(128302, R.drawable.atom_ui_emoji_1f52e);
        sEmojisMap.put(127909, R.drawable.atom_ui_emoji_1f3a5);
        sEmojisMap.put(128247, R.drawable.atom_ui_emoji_1f4f7);
        sEmojisMap.put(128249, R.drawable.atom_ui_emoji_1f4f9);
        sEmojisMap.put(128252, R.drawable.atom_ui_emoji_1f4fc);
        sEmojisMap.put(128191, R.drawable.atom_ui_emoji_1f4bf);
        sEmojisMap.put(128192, R.drawable.atom_ui_emoji_1f4c0);
        sEmojisMap.put(128189, R.drawable.atom_ui_emoji_1f4bd);
        sEmojisMap.put(128190, R.drawable.atom_ui_emoji_1f4be);
        sEmojisMap.put(128187, R.drawable.atom_ui_emoji_1f4bb);
        sEmojisMap.put(128241, R.drawable.atom_ui_emoji_1f4f1);
        sEmojisMap.put(9742, R.drawable.atom_ui_emoji_260e);
        sEmojisMap.put(128222, R.drawable.atom_ui_emoji_1f4de);
        sEmojisMap.put(128223, R.drawable.atom_ui_emoji_1f4df);
        sEmojisMap.put(128224, R.drawable.atom_ui_emoji_1f4e0);
        sEmojisMap.put(128225, R.drawable.atom_ui_emoji_1f4e1);
        sEmojisMap.put(128250, R.drawable.atom_ui_emoji_1f4fa);
        sEmojisMap.put(128251, R.drawable.atom_ui_emoji_1f4fb);
        sEmojisMap.put(128266, R.drawable.atom_ui_emoji_1f50a);
        sEmojisMap.put(128265, R.drawable.atom_ui_emoji_1f509);
        sEmojisMap.put(128264, R.drawable.atom_ui_emoji_1f508);
        sEmojisMap.put(128263, R.drawable.atom_ui_emoji_1f507);
        sEmojisMap.put(128276, R.drawable.atom_ui_emoji_1f514);
        sEmojisMap.put(128277, R.drawable.atom_ui_emoji_1f515);
        sEmojisMap.put(128226, R.drawable.atom_ui_emoji_1f4e2);
        sEmojisMap.put(128227, R.drawable.atom_ui_emoji_1f4e3);
        sEmojisMap.put(9203, R.drawable.atom_ui_emoji_23f3);
        sEmojisMap.put(8987, R.drawable.atom_ui_emoji_231b);
        sEmojisMap.put(9200, R.drawable.atom_ui_emoji_23f0);
        sEmojisMap.put(8986, R.drawable.atom_ui_emoji_231a);
        sEmojisMap.put(128275, R.drawable.atom_ui_emoji_1f513);
        sEmojisMap.put(128274, R.drawable.atom_ui_emoji_1f512);
        sEmojisMap.put(128271, R.drawable.atom_ui_emoji_1f50f);
        sEmojisMap.put(128272, R.drawable.atom_ui_emoji_1f510);
        sEmojisMap.put(128273, R.drawable.atom_ui_emoji_1f511);
        sEmojisMap.put(128270, R.drawable.atom_ui_emoji_1f50e);
        sEmojisMap.put(128161, R.drawable.atom_ui_emoji_1f4a1);
        sEmojisMap.put(128294, R.drawable.atom_ui_emoji_1f526);
        sEmojisMap.put(128262, R.drawable.atom_ui_emoji_1f506);
        sEmojisMap.put(128261, R.drawable.atom_ui_emoji_1f505);
        sEmojisMap.put(128268, R.drawable.atom_ui_emoji_1f50c);
        sEmojisMap.put(128267, R.drawable.atom_ui_emoji_1f50b);
        sEmojisMap.put(128269, R.drawable.atom_ui_emoji_1f50d);
        sEmojisMap.put(128705, R.drawable.atom_ui_emoji_1f6c1);
        sEmojisMap.put(128704, R.drawable.atom_ui_emoji_1f6c0);
        sEmojisMap.put(128703, R.drawable.atom_ui_emoji_1f6bf);
        sEmojisMap.put(128701, R.drawable.atom_ui_emoji_1f6bd);
        sEmojisMap.put(128295, R.drawable.atom_ui_emoji_1f527);
        sEmojisMap.put(128297, R.drawable.atom_ui_emoji_1f529);
        sEmojisMap.put(128296, R.drawable.atom_ui_emoji_1f528);
        sEmojisMap.put(128682, R.drawable.atom_ui_emoji_1f6aa);
        sEmojisMap.put(128684, R.drawable.atom_ui_emoji_1f6ac);
        sEmojisMap.put(128163, R.drawable.atom_ui_emoji_1f4a3);
        sEmojisMap.put(128299, R.drawable.atom_ui_emoji_1f52b);
        sEmojisMap.put(128298, R.drawable.atom_ui_emoji_1f52a);
        sEmojisMap.put(128138, R.drawable.atom_ui_emoji_1f48a);
        sEmojisMap.put(128137, R.drawable.atom_ui_emoji_1f489);
        sEmojisMap.put(128176, R.drawable.atom_ui_emoji_1f4b0);
        sEmojisMap.put(128180, R.drawable.atom_ui_emoji_1f4b4);
        sEmojisMap.put(128181, R.drawable.atom_ui_emoji_1f4b5);
        sEmojisMap.put(128183, R.drawable.atom_ui_emoji_1f4b7);
        sEmojisMap.put(128182, R.drawable.atom_ui_emoji_1f4b6);
        sEmojisMap.put(128179, R.drawable.atom_ui_emoji_1f4b3);
        sEmojisMap.put(128184, R.drawable.atom_ui_emoji_1f4b8);
        sEmojisMap.put(128242, R.drawable.atom_ui_emoji_1f4f2);
        sEmojisMap.put(128231, R.drawable.atom_ui_emoji_1f4e7);
        sEmojisMap.put(128229, R.drawable.atom_ui_emoji_1f4e5);
        sEmojisMap.put(128228, R.drawable.atom_ui_emoji_1f4e4);
        sEmojisMap.put(9993, R.drawable.atom_ui_emoji_2709);
        sEmojisMap.put(128233, R.drawable.atom_ui_emoji_1f4e9);
        sEmojisMap.put(128232, R.drawable.atom_ui_emoji_1f4e8);
        sEmojisMap.put(128239, R.drawable.atom_ui_emoji_1f4ef);
        sEmojisMap.put(128235, R.drawable.atom_ui_emoji_1f4eb);
        sEmojisMap.put(128234, R.drawable.atom_ui_emoji_1f4ea);
        sEmojisMap.put(128236, R.drawable.atom_ui_emoji_1f4ec);
        sEmojisMap.put(128237, R.drawable.atom_ui_emoji_1f4ed);
        sEmojisMap.put(128238, R.drawable.atom_ui_emoji_1f4ee);
        sEmojisMap.put(128230, R.drawable.atom_ui_emoji_1f4e6);
        sEmojisMap.put(128221, R.drawable.atom_ui_emoji_1f4dd);
        sEmojisMap.put(128196, R.drawable.atom_ui_emoji_1f4c4);
        sEmojisMap.put(128195, R.drawable.atom_ui_emoji_1f4c3);
        sEmojisMap.put(128209, R.drawable.atom_ui_emoji_1f4d1);
        sEmojisMap.put(128202, R.drawable.atom_ui_emoji_1f4ca);
        sEmojisMap.put(128200, R.drawable.atom_ui_emoji_1f4c8);
        sEmojisMap.put(128201, R.drawable.atom_ui_emoji_1f4c9);
        sEmojisMap.put(128220, R.drawable.atom_ui_emoji_1f4dc);
        sEmojisMap.put(128203, R.drawable.atom_ui_emoji_1f4cb);
        sEmojisMap.put(128197, R.drawable.atom_ui_emoji_1f4c5);
        sEmojisMap.put(128198, R.drawable.atom_ui_emoji_1f4c6);
        sEmojisMap.put(128199, R.drawable.atom_ui_emoji_1f4c7);
        sEmojisMap.put(128193, R.drawable.atom_ui_emoji_1f4c1);
        sEmojisMap.put(128194, R.drawable.atom_ui_emoji_1f4c2);
        sEmojisMap.put(9986, R.drawable.atom_ui_emoji_2702);
        sEmojisMap.put(128204, R.drawable.atom_ui_emoji_1f4cc);
        sEmojisMap.put(128206, R.drawable.atom_ui_emoji_1f4ce);
        sEmojisMap.put(10002, R.drawable.atom_ui_emoji_2712);
        sEmojisMap.put(9999, R.drawable.atom_ui_emoji_270f);
        sEmojisMap.put(128207, R.drawable.atom_ui_emoji_1f4cf);
        sEmojisMap.put(128208, R.drawable.atom_ui_emoji_1f4d0);
        sEmojisMap.put(128213, R.drawable.atom_ui_emoji_1f4d5);
        sEmojisMap.put(128215, R.drawable.atom_ui_emoji_1f4d7);
        sEmojisMap.put(128216, R.drawable.atom_ui_emoji_1f4d8);
        sEmojisMap.put(128217, R.drawable.atom_ui_emoji_1f4d9);
        sEmojisMap.put(128211, R.drawable.atom_ui_emoji_1f4d3);
        sEmojisMap.put(128212, R.drawable.atom_ui_emoji_1f4d4);
        sEmojisMap.put(128210, R.drawable.atom_ui_emoji_1f4d2);
        sEmojisMap.put(128218, R.drawable.atom_ui_emoji_1f4da);
        sEmojisMap.put(128214, R.drawable.atom_ui_emoji_1f4d6);
        sEmojisMap.put(128278, R.drawable.atom_ui_emoji_1f516);
        sEmojisMap.put(128219, R.drawable.atom_ui_emoji_1f4db);
        sEmojisMap.put(128300, R.drawable.atom_ui_emoji_1f52c);
        sEmojisMap.put(128301, R.drawable.atom_ui_emoji_1f52d);
        sEmojisMap.put(128240, R.drawable.atom_ui_emoji_1f4f0);
        sEmojisMap.put(127912, R.drawable.atom_ui_emoji_1f3a8);
        sEmojisMap.put(127916, R.drawable.atom_ui_emoji_1f3ac);
        sEmojisMap.put(127908, R.drawable.atom_ui_emoji_1f3a4);
        sEmojisMap.put(127911, R.drawable.atom_ui_emoji_1f3a7);
        sEmojisMap.put(127932, R.drawable.atom_ui_emoji_1f3bc);
        sEmojisMap.put(127925, R.drawable.atom_ui_emoji_1f3b5);
        sEmojisMap.put(127926, R.drawable.atom_ui_emoji_1f3b6);
        sEmojisMap.put(127929, R.drawable.atom_ui_emoji_1f3b9);
        sEmojisMap.put(127931, R.drawable.atom_ui_emoji_1f3bb);
        sEmojisMap.put(127930, R.drawable.atom_ui_emoji_1f3ba);
        sEmojisMap.put(127927, R.drawable.atom_ui_emoji_1f3b7);
        sEmojisMap.put(127928, R.drawable.atom_ui_emoji_1f3b8);
        sEmojisMap.put(128126, R.drawable.atom_ui_emoji_1f47e);
        sEmojisMap.put(127918, R.drawable.atom_ui_emoji_1f3ae);
        sEmojisMap.put(127183, R.drawable.atom_ui_emoji_1f0cf);
        sEmojisMap.put(127924, R.drawable.atom_ui_emoji_1f3b4);
        sEmojisMap.put(126980, R.drawable.atom_ui_emoji_1f004);
        sEmojisMap.put(127922, R.drawable.atom_ui_emoji_1f3b2);
        sEmojisMap.put(127919, R.drawable.atom_ui_emoji_1f3af);
        sEmojisMap.put(127944, R.drawable.atom_ui_emoji_1f3c8);
        sEmojisMap.put(127936, R.drawable.atom_ui_emoji_1f3c0);
        sEmojisMap.put(9917, R.drawable.atom_ui_emoji_26bd);
        sEmojisMap.put(9918, R.drawable.atom_ui_emoji_26be);
        sEmojisMap.put(127934, R.drawable.atom_ui_emoji_1f3be);
        sEmojisMap.put(127921, R.drawable.atom_ui_emoji_1f3b1);
        sEmojisMap.put(127945, R.drawable.atom_ui_emoji_1f3c9);
        sEmojisMap.put(127923, R.drawable.atom_ui_emoji_1f3b3);
        sEmojisMap.put(9971, R.drawable.atom_ui_emoji_26f3);
        sEmojisMap.put(128693, R.drawable.atom_ui_emoji_1f6b5);
        sEmojisMap.put(128692, R.drawable.atom_ui_emoji_1f6b4);
        sEmojisMap.put(127937, R.drawable.atom_ui_emoji_1f3c1);
        sEmojisMap.put(127943, R.drawable.atom_ui_emoji_1f3c7);
        sEmojisMap.put(127942, R.drawable.atom_ui_emoji_1f3c6);
        sEmojisMap.put(127935, R.drawable.atom_ui_emoji_1f3bf);
        sEmojisMap.put(127938, R.drawable.atom_ui_emoji_1f3c2);
        sEmojisMap.put(127946, R.drawable.atom_ui_emoji_1f3ca);
        sEmojisMap.put(127940, R.drawable.atom_ui_emoji_1f3c4);
        sEmojisMap.put(127907, R.drawable.atom_ui_emoji_1f3a3);
        sEmojisMap.put(9749, R.drawable.atom_ui_emoji_2615);
        sEmojisMap.put(127861, R.drawable.atom_ui_emoji_1f375);
        sEmojisMap.put(127862, R.drawable.atom_ui_emoji_1f376);
        sEmojisMap.put(127868, R.drawable.atom_ui_emoji_1f37c);
        sEmojisMap.put(127866, R.drawable.atom_ui_emoji_1f37a);
        sEmojisMap.put(127867, R.drawable.atom_ui_emoji_1f37b);
        sEmojisMap.put(127864, R.drawable.atom_ui_emoji_1f378);
        sEmojisMap.put(127865, R.drawable.atom_ui_emoji_1f379);
        sEmojisMap.put(127863, R.drawable.atom_ui_emoji_1f377);
        sEmojisMap.put(127860, R.drawable.atom_ui_emoji_1f374);
        sEmojisMap.put(127829, R.drawable.atom_ui_emoji_1f355);
        sEmojisMap.put(127828, R.drawable.atom_ui_emoji_1f354);
        sEmojisMap.put(127839, R.drawable.atom_ui_emoji_1f35f);
        sEmojisMap.put(127831, R.drawable.atom_ui_emoji_1f357);
        sEmojisMap.put(127830, R.drawable.atom_ui_emoji_1f356);
        sEmojisMap.put(127837, R.drawable.atom_ui_emoji_1f35d);
        sEmojisMap.put(127835, R.drawable.atom_ui_emoji_1f35b);
        sEmojisMap.put(127844, R.drawable.atom_ui_emoji_1f364);
        sEmojisMap.put(127857, R.drawable.atom_ui_emoji_1f371);
        sEmojisMap.put(127843, R.drawable.atom_ui_emoji_1f363);
        sEmojisMap.put(127845, R.drawable.atom_ui_emoji_1f365);
        sEmojisMap.put(127833, R.drawable.atom_ui_emoji_1f359);
        sEmojisMap.put(127832, R.drawable.atom_ui_emoji_1f358);
        sEmojisMap.put(127834, R.drawable.atom_ui_emoji_1f35a);
        sEmojisMap.put(127836, R.drawable.atom_ui_emoji_1f35c);
        sEmojisMap.put(127858, R.drawable.atom_ui_emoji_1f372);
        sEmojisMap.put(127842, R.drawable.atom_ui_emoji_1f362);
        sEmojisMap.put(127841, R.drawable.atom_ui_emoji_1f361);
        sEmojisMap.put(127859, R.drawable.atom_ui_emoji_1f373);
        sEmojisMap.put(127838, R.drawable.atom_ui_emoji_1f35e);
        sEmojisMap.put(127849, R.drawable.atom_ui_emoji_1f369);
        sEmojisMap.put(127854, R.drawable.atom_ui_emoji_1f36e);
        sEmojisMap.put(127846, R.drawable.atom_ui_emoji_1f366);
        sEmojisMap.put(127848, R.drawable.atom_ui_emoji_1f368);
        sEmojisMap.put(127847, R.drawable.atom_ui_emoji_1f367);
        sEmojisMap.put(127874, R.drawable.atom_ui_emoji_1f382);
        sEmojisMap.put(127856, R.drawable.atom_ui_emoji_1f370);
        sEmojisMap.put(127850, R.drawable.atom_ui_emoji_1f36a);
        sEmojisMap.put(127851, R.drawable.atom_ui_emoji_1f36b);
        sEmojisMap.put(127852, R.drawable.atom_ui_emoji_1f36c);
        sEmojisMap.put(127853, R.drawable.atom_ui_emoji_1f36d);
        sEmojisMap.put(127855, R.drawable.atom_ui_emoji_1f36f);
        sEmojisMap.put(127822, R.drawable.atom_ui_emoji_1f34e);
        sEmojisMap.put(127823, R.drawable.atom_ui_emoji_1f34f);
        sEmojisMap.put(127818, R.drawable.atom_ui_emoji_1f34a);
        sEmojisMap.put(127819, R.drawable.atom_ui_emoji_1f34b);
        sEmojisMap.put(127826, R.drawable.atom_ui_emoji_1f352);
        sEmojisMap.put(127815, R.drawable.atom_ui_emoji_1f347);
        sEmojisMap.put(127817, R.drawable.atom_ui_emoji_1f349);
        sEmojisMap.put(127827, R.drawable.atom_ui_emoji_1f353);
        sEmojisMap.put(127825, R.drawable.atom_ui_emoji_1f351);
        sEmojisMap.put(127816, R.drawable.atom_ui_emoji_1f348);
        sEmojisMap.put(127820, R.drawable.atom_ui_emoji_1f34c);
        sEmojisMap.put(127824, R.drawable.atom_ui_emoji_1f350);
        sEmojisMap.put(127821, R.drawable.atom_ui_emoji_1f34d);
        sEmojisMap.put(127840, R.drawable.atom_ui_emoji_1f360);
        sEmojisMap.put(127814, R.drawable.atom_ui_emoji_1f346);
        sEmojisMap.put(127813, R.drawable.atom_ui_emoji_1f345);
        sEmojisMap.put(127805, R.drawable.atom_ui_emoji_1f33d);
        sEmojisMap.put(127968, R.drawable.atom_ui_emoji_1f3e0);
        sEmojisMap.put(127969, R.drawable.atom_ui_emoji_1f3e1);
        sEmojisMap.put(127979, R.drawable.atom_ui_emoji_1f3eb);
        sEmojisMap.put(127970, R.drawable.atom_ui_emoji_1f3e2);
        sEmojisMap.put(127971, R.drawable.atom_ui_emoji_1f3e3);
        sEmojisMap.put(127973, R.drawable.atom_ui_emoji_1f3e5);
        sEmojisMap.put(127974, R.drawable.atom_ui_emoji_1f3e6);
        sEmojisMap.put(127978, R.drawable.atom_ui_emoji_1f3ea);
        sEmojisMap.put(127977, R.drawable.atom_ui_emoji_1f3e9);
        sEmojisMap.put(127976, R.drawable.atom_ui_emoji_1f3e8);
        sEmojisMap.put(128146, R.drawable.atom_ui_emoji_1f492);
        sEmojisMap.put(9962, R.drawable.atom_ui_emoji_26ea);
        sEmojisMap.put(127980, R.drawable.atom_ui_emoji_1f3ec);
        sEmojisMap.put(127972, R.drawable.atom_ui_emoji_1f3e4);
        sEmojisMap.put(127751, R.drawable.atom_ui_emoji_1f307);
        sEmojisMap.put(127750, R.drawable.atom_ui_emoji_1f306);
        sEmojisMap.put(127983, R.drawable.atom_ui_emoji_1f3ef);
        sEmojisMap.put(127984, R.drawable.atom_ui_emoji_1f3f0);
        sEmojisMap.put(9978, R.drawable.atom_ui_emoji_26fa);
        sEmojisMap.put(127981, R.drawable.atom_ui_emoji_1f3ed);
        sEmojisMap.put(128508, R.drawable.atom_ui_emoji_1f5fc);
        sEmojisMap.put(128510, R.drawable.atom_ui_emoji_1f5fe);
        sEmojisMap.put(128507, R.drawable.atom_ui_emoji_1f5fb);
        sEmojisMap.put(127748, R.drawable.atom_ui_emoji_1f304);
        sEmojisMap.put(127749, R.drawable.atom_ui_emoji_1f305);
        sEmojisMap.put(127747, R.drawable.atom_ui_emoji_1f303);
        sEmojisMap.put(128509, R.drawable.atom_ui_emoji_1f5fd);
        sEmojisMap.put(127753, R.drawable.atom_ui_emoji_1f309);
        sEmojisMap.put(127904, R.drawable.atom_ui_emoji_1f3a0);
        sEmojisMap.put(127905, R.drawable.atom_ui_emoji_1f3a1);
        sEmojisMap.put(9970, R.drawable.atom_ui_emoji_26f2);
        sEmojisMap.put(127906, R.drawable.atom_ui_emoji_1f3a2);
        sEmojisMap.put(128674, R.drawable.atom_ui_emoji_1f6a2);
        sEmojisMap.put(9973, R.drawable.atom_ui_emoji_26f5);
        sEmojisMap.put(128676, R.drawable.atom_ui_emoji_1f6a4);
        sEmojisMap.put(128675, R.drawable.atom_ui_emoji_1f6a3);
        sEmojisMap.put(9875, R.drawable.atom_ui_emoji_2693);
        sEmojisMap.put(128640, R.drawable.atom_ui_emoji_1f680);
        sEmojisMap.put(9992, R.drawable.atom_ui_emoji_2708);
        sEmojisMap.put(128186, R.drawable.atom_ui_emoji_1f4ba);
        sEmojisMap.put(128641, R.drawable.atom_ui_emoji_1f681);
        sEmojisMap.put(128642, R.drawable.atom_ui_emoji_1f682);
        sEmojisMap.put(128650, R.drawable.atom_ui_emoji_1f68a);
        sEmojisMap.put(128649, R.drawable.atom_ui_emoji_1f689);
        sEmojisMap.put(128670, R.drawable.atom_ui_emoji_1f69e);
        sEmojisMap.put(128646, R.drawable.atom_ui_emoji_1f686);
        sEmojisMap.put(128644, R.drawable.atom_ui_emoji_1f684);
        sEmojisMap.put(128645, R.drawable.atom_ui_emoji_1f685);
        sEmojisMap.put(128648, R.drawable.atom_ui_emoji_1f688);
        sEmojisMap.put(128647, R.drawable.atom_ui_emoji_1f687);
        sEmojisMap.put(128669, R.drawable.atom_ui_emoji_1f69d);
        sEmojisMap.put(128651, R.drawable.atom_ui_emoji_1f68b);
        sEmojisMap.put(128643, R.drawable.atom_ui_emoji_1f683);
        sEmojisMap.put(128654, R.drawable.atom_ui_emoji_1f68e);
        sEmojisMap.put(128652, R.drawable.atom_ui_emoji_1f68c);
        sEmojisMap.put(128653, R.drawable.atom_ui_emoji_1f68d);
        sEmojisMap.put(128665, R.drawable.atom_ui_emoji_1f699);
        sEmojisMap.put(128664, R.drawable.atom_ui_emoji_1f698);
        sEmojisMap.put(128663, R.drawable.atom_ui_emoji_1f697);
        sEmojisMap.put(128661, R.drawable.atom_ui_emoji_1f695);
        sEmojisMap.put(128662, R.drawable.atom_ui_emoji_1f696);
        sEmojisMap.put(128667, R.drawable.atom_ui_emoji_1f69b);
        sEmojisMap.put(128666, R.drawable.atom_ui_emoji_1f69a);
        sEmojisMap.put(128680, R.drawable.atom_ui_emoji_1f6a8);
        sEmojisMap.put(128659, R.drawable.atom_ui_emoji_1f693);
        sEmojisMap.put(128660, R.drawable.atom_ui_emoji_1f694);
        sEmojisMap.put(128658, R.drawable.atom_ui_emoji_1f692);
        sEmojisMap.put(128657, R.drawable.atom_ui_emoji_1f691);
        sEmojisMap.put(128656, R.drawable.atom_ui_emoji_1f690);
        sEmojisMap.put(128690, R.drawable.atom_ui_emoji_1f6b2);
        sEmojisMap.put(128673, R.drawable.atom_ui_emoji_1f6a1);
        sEmojisMap.put(128671, R.drawable.atom_ui_emoji_1f69f);
        sEmojisMap.put(128672, R.drawable.atom_ui_emoji_1f6a0);
        sEmojisMap.put(128668, R.drawable.atom_ui_emoji_1f69c);
        sEmojisMap.put(128136, R.drawable.atom_ui_emoji_1f488);
        sEmojisMap.put(128655, R.drawable.atom_ui_emoji_1f68f);
        sEmojisMap.put(127915, R.drawable.atom_ui_emoji_1f3ab);
        sEmojisMap.put(128678, R.drawable.atom_ui_emoji_1f6a6);
        sEmojisMap.put(128677, R.drawable.atom_ui_emoji_1f6a5);
        sEmojisMap.put(9888, R.drawable.atom_ui_emoji_26a0);
        sEmojisMap.put(128679, R.drawable.atom_ui_emoji_1f6a7);
        sEmojisMap.put(128304, R.drawable.atom_ui_emoji_1f530);
        sEmojisMap.put(9981, R.drawable.atom_ui_emoji_26fd);
        sEmojisMap.put(127982, R.drawable.atom_ui_emoji_1f3ee);
        sEmojisMap.put(127920, R.drawable.atom_ui_emoji_1f3b0);
        sEmojisMap.put(9832, R.drawable.atom_ui_emoji_2668);
        sEmojisMap.put(128511, R.drawable.atom_ui_emoji_1f5ff);
        sEmojisMap.put(127914, R.drawable.atom_ui_emoji_1f3aa);
        sEmojisMap.put(127917, R.drawable.atom_ui_emoji_1f3ad);
        sEmojisMap.put(128205, R.drawable.atom_ui_emoji_1f4cd);
        sEmojisMap.put(128681, R.drawable.atom_ui_emoji_1f6a9);
        sEmojisMap.put(128287, R.drawable.atom_ui_emoji_1f51f);
        sEmojisMap.put(128290, R.drawable.atom_ui_emoji_1f522);
        sEmojisMap.put(128291, R.drawable.atom_ui_emoji_1f523);
        sEmojisMap.put(11014, R.drawable.atom_ui_emoji_2b06);
        sEmojisMap.put(11015, R.drawable.atom_ui_emoji_2b07);
        sEmojisMap.put(11013, R.drawable.atom_ui_emoji_2b05);
        sEmojisMap.put(10145, R.drawable.atom_ui_emoji_27a1);
        sEmojisMap.put(128288, R.drawable.atom_ui_emoji_1f520);
        sEmojisMap.put(128289, R.drawable.atom_ui_emoji_1f521);
        sEmojisMap.put(128292, R.drawable.atom_ui_emoji_1f524);
        sEmojisMap.put(8599, R.drawable.atom_ui_emoji_2197);
        sEmojisMap.put(8598, R.drawable.atom_ui_emoji_2196);
        sEmojisMap.put(8600, R.drawable.atom_ui_emoji_2198);
        sEmojisMap.put(8601, R.drawable.atom_ui_emoji_2199);
        sEmojisMap.put(8596, R.drawable.atom_ui_emoji_2194);
        sEmojisMap.put(8597, R.drawable.atom_ui_emoji_2195);
        sEmojisMap.put(128260, R.drawable.atom_ui_emoji_1f504);
        sEmojisMap.put(9664, R.drawable.atom_ui_emoji_25c0);
        sEmojisMap.put(9654, R.drawable.atom_ui_emoji_25b6);
        sEmojisMap.put(128316, R.drawable.atom_ui_emoji_1f53c);
        sEmojisMap.put(128317, R.drawable.atom_ui_emoji_1f53d);
        sEmojisMap.put(8617, R.drawable.atom_ui_emoji_21a9);
        sEmojisMap.put(8618, R.drawable.atom_ui_emoji_21aa);
        sEmojisMap.put(8505, R.drawable.atom_ui_emoji_2139);
        sEmojisMap.put(9194, R.drawable.atom_ui_emoji_23ea);
        sEmojisMap.put(9193, R.drawable.atom_ui_emoji_23e9);
        sEmojisMap.put(9195, R.drawable.atom_ui_emoji_23eb);
        sEmojisMap.put(9196, R.drawable.atom_ui_emoji_23ec);
        sEmojisMap.put(10549, R.drawable.atom_ui_emoji_2935);
        sEmojisMap.put(10548, R.drawable.atom_ui_emoji_2934);
        sEmojisMap.put(127383, R.drawable.atom_ui_emoji_1f197);
        sEmojisMap.put(128256, R.drawable.atom_ui_emoji_1f500);
        sEmojisMap.put(128257, R.drawable.atom_ui_emoji_1f501);
        sEmojisMap.put(128258, R.drawable.atom_ui_emoji_1f502);
        sEmojisMap.put(127381, R.drawable.atom_ui_emoji_1f195);
        sEmojisMap.put(127385, R.drawable.atom_ui_emoji_1f199);
        sEmojisMap.put(127378, R.drawable.atom_ui_emoji_1f192);
        sEmojisMap.put(127379, R.drawable.atom_ui_emoji_1f193);
        sEmojisMap.put(127382, R.drawable.atom_ui_emoji_1f196);
        sEmojisMap.put(128246, R.drawable.atom_ui_emoji_1f4f6);
        sEmojisMap.put(127910, R.drawable.atom_ui_emoji_1f3a6);
        sEmojisMap.put(127489, R.drawable.atom_ui_emoji_1f201);
        sEmojisMap.put(127535, R.drawable.atom_ui_emoji_1f22f);
        sEmojisMap.put(127539, R.drawable.atom_ui_emoji_1f233);
        sEmojisMap.put(127541, R.drawable.atom_ui_emoji_1f235);
        sEmojisMap.put(127540, R.drawable.atom_ui_emoji_1f234);
        sEmojisMap.put(127538, R.drawable.atom_ui_emoji_1f232);
        sEmojisMap.put(127568, R.drawable.atom_ui_emoji_1f250);
        sEmojisMap.put(127545, R.drawable.atom_ui_emoji_1f239);
        sEmojisMap.put(127546, R.drawable.atom_ui_emoji_1f23a);
        sEmojisMap.put(127542, R.drawable.atom_ui_emoji_1f236);
        sEmojisMap.put(127514, R.drawable.atom_ui_emoji_1f21a);
        sEmojisMap.put(128699, R.drawable.atom_ui_emoji_1f6bb);
        sEmojisMap.put(128697, R.drawable.atom_ui_emoji_1f6b9);
        sEmojisMap.put(128698, R.drawable.atom_ui_emoji_1f6ba);
        sEmojisMap.put(128700, R.drawable.atom_ui_emoji_1f6bc);
        sEmojisMap.put(128702, R.drawable.atom_ui_emoji_1f6be);
        sEmojisMap.put(128688, R.drawable.atom_ui_emoji_1f6b0);
        sEmojisMap.put(128686, R.drawable.atom_ui_emoji_1f6ae);
        sEmojisMap.put(127359, R.drawable.atom_ui_emoji_1f17f);
        sEmojisMap.put(9855, R.drawable.atom_ui_emoji_267f);
        sEmojisMap.put(128685, R.drawable.atom_ui_emoji_1f6ad);
        sEmojisMap.put(127543, R.drawable.atom_ui_emoji_1f237);
        sEmojisMap.put(127544, R.drawable.atom_ui_emoji_1f238);
        sEmojisMap.put(127490, R.drawable.atom_ui_emoji_1f202);
        sEmojisMap.put(9410, R.drawable.atom_ui_emoji_24c2);
        sEmojisMap.put(128706, R.drawable.atom_ui_emoji_1f6c2);
        sEmojisMap.put(128708, R.drawable.atom_ui_emoji_1f6c4);
        sEmojisMap.put(128709, R.drawable.atom_ui_emoji_1f6c5);
        sEmojisMap.put(128707, R.drawable.atom_ui_emoji_1f6c3);
        sEmojisMap.put(127569, R.drawable.atom_ui_emoji_1f251);
        sEmojisMap.put(12953, R.drawable.atom_ui_emoji_3299);
        sEmojisMap.put(12951, R.drawable.atom_ui_emoji_3297);
        sEmojisMap.put(127377, R.drawable.atom_ui_emoji_1f191);
        sEmojisMap.put(127384, R.drawable.atom_ui_emoji_1f198);
        sEmojisMap.put(127380, R.drawable.atom_ui_emoji_1f194);
        sEmojisMap.put(128683, R.drawable.atom_ui_emoji_1f6ab);
        sEmojisMap.put(128286, R.drawable.atom_ui_emoji_1f51e);
        sEmojisMap.put(128245, R.drawable.atom_ui_emoji_1f4f5);
        sEmojisMap.put(128687, R.drawable.atom_ui_emoji_1f6af);
        sEmojisMap.put(128689, R.drawable.atom_ui_emoji_1f6b1);
        sEmojisMap.put(128691, R.drawable.atom_ui_emoji_1f6b3);
        sEmojisMap.put(128695, R.drawable.atom_ui_emoji_1f6b7);
        sEmojisMap.put(128696, R.drawable.atom_ui_emoji_1f6b8);
        sEmojisMap.put(9940, R.drawable.atom_ui_emoji_26d4);
        sEmojisMap.put(10035, R.drawable.atom_ui_emoji_2733);
        sEmojisMap.put(10055, R.drawable.atom_ui_emoji_2747);
        sEmojisMap.put(10062, R.drawable.atom_ui_emoji_274e);
        sEmojisMap.put(9989, R.drawable.atom_ui_emoji_2705);
        sEmojisMap.put(10036, R.drawable.atom_ui_emoji_2734);
        sEmojisMap.put(128159, R.drawable.atom_ui_emoji_1f49f);
        sEmojisMap.put(127386, R.drawable.atom_ui_emoji_1f19a);
        sEmojisMap.put(128243, R.drawable.atom_ui_emoji_1f4f3);
        sEmojisMap.put(128244, R.drawable.atom_ui_emoji_1f4f4);
        sEmojisMap.put(127344, R.drawable.atom_ui_emoji_1f170);
        sEmojisMap.put(127345, R.drawable.atom_ui_emoji_1f171);
        sEmojisMap.put(127374, R.drawable.atom_ui_emoji_1f18e);
        sEmojisMap.put(127358, R.drawable.atom_ui_emoji_1f17e);
        sEmojisMap.put(128160, R.drawable.atom_ui_emoji_1f4a0);
        sEmojisMap.put(10175, R.drawable.atom_ui_emoji_27bf);
        sEmojisMap.put(9851, R.drawable.atom_ui_emoji_267b);
        sEmojisMap.put(9800, R.drawable.atom_ui_emoji_2648);
        sEmojisMap.put(9801, R.drawable.atom_ui_emoji_2649);
        sEmojisMap.put(9802, R.drawable.atom_ui_emoji_264a);
        sEmojisMap.put(9803, R.drawable.atom_ui_emoji_264b);
        sEmojisMap.put(9804, R.drawable.atom_ui_emoji_264c);
        sEmojisMap.put(9805, R.drawable.atom_ui_emoji_264d);
        sEmojisMap.put(9806, R.drawable.atom_ui_emoji_264e);
        sEmojisMap.put(9807, R.drawable.atom_ui_emoji_264f);
        sEmojisMap.put(9808, R.drawable.atom_ui_emoji_2650);
        sEmojisMap.put(9809, R.drawable.atom_ui_emoji_2651);
        sEmojisMap.put(9810, R.drawable.atom_ui_emoji_2652);
        sEmojisMap.put(9811, R.drawable.atom_ui_emoji_2653);
        sEmojisMap.put(9934, R.drawable.atom_ui_emoji_26ce);
        sEmojisMap.put(128303, R.drawable.atom_ui_emoji_1f52f);
        sEmojisMap.put(127975, R.drawable.atom_ui_emoji_1f3e7);
        sEmojisMap.put(128185, R.drawable.atom_ui_emoji_1f4b9);
        sEmojisMap.put(128178, R.drawable.atom_ui_emoji_1f4b2);
        sEmojisMap.put(128177, R.drawable.atom_ui_emoji_1f4b1);
        sEmojisMap.put(169, R.drawable.atom_ui_emoji_00a9);
        sEmojisMap.put(174, R.drawable.atom_ui_emoji_00ae);
        sEmojisMap.put(8482, R.drawable.atom_ui_emoji_2122);
        sEmojisMap.put(10060, R.drawable.atom_ui_emoji_274c);
        sEmojisMap.put(8252, R.drawable.atom_ui_emoji_203c);
        sEmojisMap.put(8265, R.drawable.atom_ui_emoji_2049);
        sEmojisMap.put(10071, R.drawable.atom_ui_emoji_2757);
        sEmojisMap.put(10067, R.drawable.atom_ui_emoji_2753);
        sEmojisMap.put(10069, R.drawable.atom_ui_emoji_2755);
        sEmojisMap.put(10068, R.drawable.atom_ui_emoji_2754);
        sEmojisMap.put(11093, R.drawable.atom_ui_emoji_2b55);
        sEmojisMap.put(128285, R.drawable.atom_ui_emoji_1f51d);
        sEmojisMap.put(128282, R.drawable.atom_ui_emoji_1f51a);
        sEmojisMap.put(128281, R.drawable.atom_ui_emoji_1f519);
        sEmojisMap.put(128283, R.drawable.atom_ui_emoji_1f51b);
        sEmojisMap.put(128284, R.drawable.atom_ui_emoji_1f51c);
        sEmojisMap.put(128259, R.drawable.atom_ui_emoji_1f503);
        sEmojisMap.put(128347, R.drawable.atom_ui_emoji_1f55b);
        sEmojisMap.put(128359, R.drawable.atom_ui_emoji_1f567);
        sEmojisMap.put(128336, R.drawable.atom_ui_emoji_1f550);
        sEmojisMap.put(128348, R.drawable.atom_ui_emoji_1f55c);
        sEmojisMap.put(128337, R.drawable.atom_ui_emoji_1f551);
        sEmojisMap.put(128349, R.drawable.atom_ui_emoji_1f55d);
        sEmojisMap.put(128338, R.drawable.atom_ui_emoji_1f552);
        sEmojisMap.put(128350, R.drawable.atom_ui_emoji_1f55e);
        sEmojisMap.put(128339, R.drawable.atom_ui_emoji_1f553);
        sEmojisMap.put(128351, R.drawable.atom_ui_emoji_1f55f);
        sEmojisMap.put(128340, R.drawable.atom_ui_emoji_1f554);
        sEmojisMap.put(128352, R.drawable.atom_ui_emoji_1f560);
        sEmojisMap.put(128341, R.drawable.atom_ui_emoji_1f555);
        sEmojisMap.put(128342, R.drawable.atom_ui_emoji_1f556);
        sEmojisMap.put(128343, R.drawable.atom_ui_emoji_1f557);
        sEmojisMap.put(128344, R.drawable.atom_ui_emoji_1f558);
        sEmojisMap.put(128345, R.drawable.atom_ui_emoji_1f559);
        sEmojisMap.put(128346, R.drawable.atom_ui_emoji_1f55a);
        sEmojisMap.put(128353, R.drawable.atom_ui_emoji_1f561);
        sEmojisMap.put(128354, R.drawable.atom_ui_emoji_1f562);
        sEmojisMap.put(128355, R.drawable.atom_ui_emoji_1f563);
        sEmojisMap.put(128356, R.drawable.atom_ui_emoji_1f564);
        sEmojisMap.put(128357, R.drawable.atom_ui_emoji_1f565);
        sEmojisMap.put(128358, R.drawable.atom_ui_emoji_1f566);
        sEmojisMap.put(10006, R.drawable.atom_ui_emoji_2716);
        sEmojisMap.put(10133, R.drawable.atom_ui_emoji_2795);
        sEmojisMap.put(10134, R.drawable.atom_ui_emoji_2796);
        sEmojisMap.put(10135, R.drawable.atom_ui_emoji_2797);
        sEmojisMap.put(9824, R.drawable.atom_ui_emoji_2660);
        sEmojisMap.put(9829, R.drawable.atom_ui_emoji_2665);
        sEmojisMap.put(9827, R.drawable.atom_ui_emoji_2663);
        sEmojisMap.put(9830, R.drawable.atom_ui_emoji_2666);
        sEmojisMap.put(128174, R.drawable.atom_ui_emoji_1f4ae);
        sEmojisMap.put(128175, R.drawable.atom_ui_emoji_1f4af);
        sEmojisMap.put(10004, R.drawable.atom_ui_emoji_2714);
        sEmojisMap.put(9745, R.drawable.atom_ui_emoji_2611);
        sEmojisMap.put(128280, R.drawable.atom_ui_emoji_1f518);
        sEmojisMap.put(128279, R.drawable.atom_ui_emoji_1f517);
        sEmojisMap.put(10160, R.drawable.atom_ui_emoji_27b0);
        sEmojisMap.put(12336, R.drawable.atom_ui_emoji_3030);
        sEmojisMap.put(12349, R.drawable.atom_ui_emoji_303d);
        sEmojisMap.put(128305, R.drawable.atom_ui_emoji_1f531);
        sEmojisMap.put(9724, R.drawable.atom_ui_emoji_25fc);
        sEmojisMap.put(9723, R.drawable.atom_ui_emoji_25fb);
        sEmojisMap.put(9726, R.drawable.atom_ui_emoji_25fe);
        sEmojisMap.put(9725, R.drawable.atom_ui_emoji_25fd);
        sEmojisMap.put(9642, R.drawable.atom_ui_emoji_25aa);
        sEmojisMap.put(9643, R.drawable.atom_ui_emoji_25ab);
        sEmojisMap.put(128314, R.drawable.atom_ui_emoji_1f53a);
        sEmojisMap.put(128306, R.drawable.atom_ui_emoji_1f532);
        sEmojisMap.put(128307, R.drawable.atom_ui_emoji_1f533);
        sEmojisMap.put(9899, R.drawable.atom_ui_emoji_26ab);
        sEmojisMap.put(9898, R.drawable.atom_ui_emoji_26aa);
        sEmojisMap.put(128308, R.drawable.atom_ui_emoji_1f534);
        sEmojisMap.put(128309, R.drawable.atom_ui_emoji_1f535);
        sEmojisMap.put(128315, R.drawable.atom_ui_emoji_1f53b);
        sEmojisMap.put(11036, R.drawable.atom_ui_emoji_2b1c);
        sEmojisMap.put(11035, R.drawable.atom_ui_emoji_2b1b);
        sEmojisMap.put(128310, R.drawable.atom_ui_emoji_1f536);
        sEmojisMap.put(128311, R.drawable.atom_ui_emoji_1f537);
        sEmojisMap.put(128312, R.drawable.atom_ui_emoji_1f538);
        sEmojisMap.put(128313, R.drawable.atom_ui_emoji_1f539);
        sSoftbanksMap.put(57345, R.drawable.atom_ui_emoji_1f466);
        sSoftbanksMap.put(57346, R.drawable.atom_ui_emoji_1f467);
        sSoftbanksMap.put(57347, R.drawable.atom_ui_emoji_1f48b);
        sSoftbanksMap.put(57348, R.drawable.atom_ui_emoji_1f468);
        sSoftbanksMap.put(57349, R.drawable.atom_ui_emoji_1f469);
        sSoftbanksMap.put(57350, R.drawable.atom_ui_emoji_1f455);
        sSoftbanksMap.put(57351, R.drawable.atom_ui_emoji_1f45e);
        sSoftbanksMap.put(57352, R.drawable.atom_ui_emoji_1f4f7);
        sSoftbanksMap.put(57353, R.drawable.atom_ui_emoji_1f4de);
        sSoftbanksMap.put(57354, R.drawable.atom_ui_emoji_1f4f1);
        sSoftbanksMap.put(57355, R.drawable.atom_ui_emoji_1f4e0);
        sSoftbanksMap.put(57356, R.drawable.atom_ui_emoji_1f4bb);
        sSoftbanksMap.put(57357, R.drawable.atom_ui_emoji_1f44a);
        sSoftbanksMap.put(57358, R.drawable.atom_ui_emoji_1f44d);
        sSoftbanksMap.put(57359, R.drawable.atom_ui_emoji_261d);
        sSoftbanksMap.put(57360, R.drawable.atom_ui_emoji_270a);
        sSoftbanksMap.put(57361, R.drawable.atom_ui_emoji_270c);
        sSoftbanksMap.put(57362, R.drawable.atom_ui_emoji_1f64b);
        sSoftbanksMap.put(57363, R.drawable.atom_ui_emoji_1f3bf);
        sSoftbanksMap.put(57364, R.drawable.atom_ui_emoji_26f3);
        sSoftbanksMap.put(57365, R.drawable.atom_ui_emoji_1f3be);
        sSoftbanksMap.put(57366, R.drawable.atom_ui_emoji_26be);
        sSoftbanksMap.put(57367, R.drawable.atom_ui_emoji_1f3c4);
        sSoftbanksMap.put(57368, R.drawable.atom_ui_emoji_26bd);
        sSoftbanksMap.put(57369, R.drawable.atom_ui_emoji_1f3a3);
        sSoftbanksMap.put(57370, R.drawable.atom_ui_emoji_1f434);
        sSoftbanksMap.put(57371, R.drawable.atom_ui_emoji_1f697);
        sSoftbanksMap.put(57372, R.drawable.atom_ui_emoji_26f5);
        sSoftbanksMap.put(57373, R.drawable.atom_ui_emoji_2708);
        sSoftbanksMap.put(57374, R.drawable.atom_ui_emoji_1f683);
        sSoftbanksMap.put(57375, R.drawable.atom_ui_emoji_1f685);
        sSoftbanksMap.put(57376, R.drawable.atom_ui_emoji_2753);
        sSoftbanksMap.put(57377, R.drawable.atom_ui_emoji_2757);
        sSoftbanksMap.put(57378, R.drawable.atom_ui_emoji_2764);
        sSoftbanksMap.put(57379, R.drawable.atom_ui_emoji_1f494);
        sSoftbanksMap.put(57380, R.drawable.atom_ui_emoji_1f550);
        sSoftbanksMap.put(57381, R.drawable.atom_ui_emoji_1f551);
        sSoftbanksMap.put(57382, R.drawable.atom_ui_emoji_1f552);
        sSoftbanksMap.put(57383, R.drawable.atom_ui_emoji_1f553);
        sSoftbanksMap.put(57384, R.drawable.atom_ui_emoji_1f554);
        sSoftbanksMap.put(57385, R.drawable.atom_ui_emoji_1f555);
        sSoftbanksMap.put(57386, R.drawable.atom_ui_emoji_1f556);
        sSoftbanksMap.put(57387, R.drawable.atom_ui_emoji_1f557);
        sSoftbanksMap.put(57388, R.drawable.atom_ui_emoji_1f558);
        sSoftbanksMap.put(57389, R.drawable.atom_ui_emoji_1f559);
        sSoftbanksMap.put(57390, R.drawable.atom_ui_emoji_1f55a);
        sSoftbanksMap.put(57391, R.drawable.atom_ui_emoji_1f55b);
        sSoftbanksMap.put(57392, R.drawable.atom_ui_emoji_1f338);
        sSoftbanksMap.put(57393, R.drawable.atom_ui_emoji_1f531);
        sSoftbanksMap.put(57394, R.drawable.atom_ui_emoji_1f339);
        sSoftbanksMap.put(57395, R.drawable.atom_ui_emoji_1f384);
        sSoftbanksMap.put(57396, R.drawable.atom_ui_emoji_1f48d);
        sSoftbanksMap.put(57397, R.drawable.atom_ui_emoji_1f48e);
        sSoftbanksMap.put(57398, R.drawable.atom_ui_emoji_1f3e0);
        sSoftbanksMap.put(57399, R.drawable.atom_ui_emoji_26ea);
        sSoftbanksMap.put(57400, R.drawable.atom_ui_emoji_1f3e2);
        sSoftbanksMap.put(57401, R.drawable.atom_ui_emoji_1f689);
        sSoftbanksMap.put(57402, R.drawable.atom_ui_emoji_26fd);
        sSoftbanksMap.put(57403, R.drawable.atom_ui_emoji_1f5fb);
        sSoftbanksMap.put(57404, R.drawable.atom_ui_emoji_1f3a4);
        sSoftbanksMap.put(57405, R.drawable.atom_ui_emoji_1f3a5);
        sSoftbanksMap.put(57406, R.drawable.atom_ui_emoji_1f3b5);
        sSoftbanksMap.put(57407, R.drawable.atom_ui_emoji_1f511);
        sSoftbanksMap.put(57408, R.drawable.atom_ui_emoji_1f3b7);
        sSoftbanksMap.put(57409, R.drawable.atom_ui_emoji_1f3b8);
        sSoftbanksMap.put(57410, R.drawable.atom_ui_emoji_1f3ba);
        sSoftbanksMap.put(57411, R.drawable.atom_ui_emoji_1f374);
        sSoftbanksMap.put(57412, R.drawable.atom_ui_emoji_1f377);
        sSoftbanksMap.put(57413, R.drawable.atom_ui_emoji_2615);
        sSoftbanksMap.put(57414, R.drawable.atom_ui_emoji_1f370);
        sSoftbanksMap.put(57415, R.drawable.atom_ui_emoji_1f37a);
        sSoftbanksMap.put(57416, R.drawable.atom_ui_emoji_26c4);
        sSoftbanksMap.put(57417, R.drawable.atom_ui_emoji_2601);
        sSoftbanksMap.put(57418, R.drawable.atom_ui_emoji_2600);
        sSoftbanksMap.put(57419, R.drawable.atom_ui_emoji_2614);
        sSoftbanksMap.put(57420, R.drawable.atom_ui_emoji_1f313);
        sSoftbanksMap.put(57421, R.drawable.atom_ui_emoji_1f304);
        sSoftbanksMap.put(57422, R.drawable.atom_ui_emoji_1f47c);
        sSoftbanksMap.put(57423, R.drawable.atom_ui_emoji_1f431);
        sSoftbanksMap.put(57424, R.drawable.atom_ui_emoji_1f42f);
        sSoftbanksMap.put(57425, R.drawable.atom_ui_emoji_1f43b);
        sSoftbanksMap.put(57426, R.drawable.atom_ui_emoji_1f429);
        sSoftbanksMap.put(57427, R.drawable.atom_ui_emoji_1f42d);
        sSoftbanksMap.put(57428, R.drawable.atom_ui_emoji_1f433);
        sSoftbanksMap.put(57429, R.drawable.atom_ui_emoji_1f427);
        sSoftbanksMap.put(57430, R.drawable.atom_ui_emoji_1f60a);
        sSoftbanksMap.put(57431, R.drawable.atom_ui_emoji_1f603);
        sSoftbanksMap.put(57432, R.drawable.atom_ui_emoji_1f61e);
        sSoftbanksMap.put(57433, R.drawable.atom_ui_emoji_1f620);
        sSoftbanksMap.put(57434, R.drawable.atom_ui_emoji_1f4a9);
        sSoftbanksMap.put(57601, R.drawable.atom_ui_emoji_1f4ea);
        sSoftbanksMap.put(57602, R.drawable.atom_ui_emoji_1f4ee);
        sSoftbanksMap.put(57603, R.drawable.atom_ui_emoji_1f4e7);
        sSoftbanksMap.put(57604, R.drawable.atom_ui_emoji_1f4f2);
        sSoftbanksMap.put(57605, R.drawable.atom_ui_emoji_1f61c);
        sSoftbanksMap.put(57606, R.drawable.atom_ui_emoji_1f60d);
        sSoftbanksMap.put(57607, R.drawable.atom_ui_emoji_1f631);
        sSoftbanksMap.put(57608, R.drawable.atom_ui_emoji_1f613);
        sSoftbanksMap.put(57609, R.drawable.atom_ui_emoji_1f435);
        sSoftbanksMap.put(57610, R.drawable.atom_ui_emoji_1f419);
        sSoftbanksMap.put(57611, R.drawable.atom_ui_emoji_1f437);
        sSoftbanksMap.put(57612, R.drawable.atom_ui_emoji_1f47d);
        sSoftbanksMap.put(57613, R.drawable.atom_ui_emoji_1f680);
        sSoftbanksMap.put(57614, R.drawable.atom_ui_emoji_1f451);
        sSoftbanksMap.put(57615, R.drawable.atom_ui_emoji_1f4a1);
        sSoftbanksMap.put(57616, R.drawable.atom_ui_emoji_1f331);
        sSoftbanksMap.put(57617, R.drawable.atom_ui_emoji_1f48f);
        sSoftbanksMap.put(57618, R.drawable.atom_ui_emoji_1f381);
        sSoftbanksMap.put(57619, R.drawable.atom_ui_emoji_1f52b);
        sSoftbanksMap.put(57620, R.drawable.atom_ui_emoji_1f50d);
        sSoftbanksMap.put(57621, R.drawable.atom_ui_emoji_1f3c3);
        sSoftbanksMap.put(57622, R.drawable.atom_ui_emoji_1f528);
        sSoftbanksMap.put(57623, R.drawable.atom_ui_emoji_1f386);
        sSoftbanksMap.put(57624, R.drawable.atom_ui_emoji_1f341);
        sSoftbanksMap.put(57625, R.drawable.atom_ui_emoji_1f342);
        sSoftbanksMap.put(57626, R.drawable.atom_ui_emoji_1f47f);
        sSoftbanksMap.put(57627, R.drawable.atom_ui_emoji_1f47b);
        sSoftbanksMap.put(57628, R.drawable.atom_ui_emoji_1f480);
        sSoftbanksMap.put(57629, R.drawable.atom_ui_emoji_1f525);
        sSoftbanksMap.put(57630, R.drawable.atom_ui_emoji_1f4bc);
        sSoftbanksMap.put(57631, R.drawable.atom_ui_emoji_1f4ba);
        sSoftbanksMap.put(57632, R.drawable.atom_ui_emoji_1f354);
        sSoftbanksMap.put(57633, R.drawable.atom_ui_emoji_26f2);
        sSoftbanksMap.put(57634, R.drawable.atom_ui_emoji_26fa);
        sSoftbanksMap.put(57635, R.drawable.atom_ui_emoji_2668);
        sSoftbanksMap.put(57636, R.drawable.atom_ui_emoji_1f3a1);
        sSoftbanksMap.put(57637, R.drawable.atom_ui_emoji_1f3ab);
        sSoftbanksMap.put(57638, R.drawable.atom_ui_emoji_1f4bf);
        sSoftbanksMap.put(57639, R.drawable.atom_ui_emoji_1f4c0);
        sSoftbanksMap.put(57640, R.drawable.atom_ui_emoji_1f4fb);
        sSoftbanksMap.put(57641, R.drawable.atom_ui_emoji_1f4fc);
        sSoftbanksMap.put(57642, R.drawable.atom_ui_emoji_1f4fa);
        sSoftbanksMap.put(57643, R.drawable.atom_ui_emoji_1f47e);
        sSoftbanksMap.put(57644, R.drawable.atom_ui_emoji_303d);
        sSoftbanksMap.put(57645, R.drawable.atom_ui_emoji_1f004);
        sSoftbanksMap.put(57646, R.drawable.atom_ui_emoji_1f19a);
        sSoftbanksMap.put(57647, R.drawable.atom_ui_emoji_1f4b0);
        sSoftbanksMap.put(57648, R.drawable.atom_ui_emoji_1f3af);
        sSoftbanksMap.put(57649, R.drawable.atom_ui_emoji_1f3c6);
        sSoftbanksMap.put(57650, R.drawable.atom_ui_emoji_1f3c1);
        sSoftbanksMap.put(57651, R.drawable.atom_ui_emoji_1f3b0);
        sSoftbanksMap.put(57652, R.drawable.atom_ui_emoji_1f40e);
        sSoftbanksMap.put(57653, R.drawable.atom_ui_emoji_1f6a4);
        sSoftbanksMap.put(57654, R.drawable.atom_ui_emoji_1f6b2);
        sSoftbanksMap.put(57655, R.drawable.atom_ui_emoji_1f6a7);
        sSoftbanksMap.put(57656, R.drawable.atom_ui_emoji_1f6b9);
        sSoftbanksMap.put(57657, R.drawable.atom_ui_emoji_1f6ba);
        sSoftbanksMap.put(57658, R.drawable.atom_ui_emoji_1f6bc);
        sSoftbanksMap.put(57659, R.drawable.atom_ui_emoji_1f489);
        sSoftbanksMap.put(57660, R.drawable.atom_ui_emoji_1f4a4);
        sSoftbanksMap.put(57661, R.drawable.atom_ui_emoji_26a1);
        sSoftbanksMap.put(57662, R.drawable.atom_ui_emoji_1f460);
        sSoftbanksMap.put(57663, R.drawable.atom_ui_emoji_1f6c0);
        sSoftbanksMap.put(57664, R.drawable.atom_ui_emoji_1f6bd);
        sSoftbanksMap.put(57665, R.drawable.atom_ui_emoji_1f50a);
        sSoftbanksMap.put(57666, R.drawable.atom_ui_emoji_1f4e2);
        sSoftbanksMap.put(57667, R.drawable.atom_ui_emoji_1f38c);
        sSoftbanksMap.put(57668, R.drawable.atom_ui_emoji_1f50f);
        sSoftbanksMap.put(57669, R.drawable.atom_ui_emoji_1f513);
        sSoftbanksMap.put(57670, R.drawable.atom_ui_emoji_1f306);
        sSoftbanksMap.put(57671, R.drawable.atom_ui_emoji_1f373);
        sSoftbanksMap.put(57672, R.drawable.atom_ui_emoji_1f4c7);
        sSoftbanksMap.put(57673, R.drawable.atom_ui_emoji_1f4b1);
        sSoftbanksMap.put(57674, R.drawable.atom_ui_emoji_1f4b9);
        sSoftbanksMap.put(57675, R.drawable.atom_ui_emoji_1f4e1);
        sSoftbanksMap.put(57676, R.drawable.atom_ui_emoji_1f4aa);
        sSoftbanksMap.put(57677, R.drawable.atom_ui_emoji_1f3e6);
        sSoftbanksMap.put(57678, R.drawable.atom_ui_emoji_1f6a5);
        sSoftbanksMap.put(57679, R.drawable.atom_ui_emoji_1f17f);
        sSoftbanksMap.put(57680, R.drawable.atom_ui_emoji_1f68f);
        sSoftbanksMap.put(57681, R.drawable.atom_ui_emoji_1f6bb);
        sSoftbanksMap.put(57682, R.drawable.atom_ui_emoji_1f46e);
        sSoftbanksMap.put(57683, R.drawable.atom_ui_emoji_1f3e3);
        sSoftbanksMap.put(57684, R.drawable.atom_ui_emoji_1f3e7);
        sSoftbanksMap.put(57685, R.drawable.atom_ui_emoji_1f3e5);
        sSoftbanksMap.put(57686, R.drawable.atom_ui_emoji_1f3ea);
        sSoftbanksMap.put(57687, R.drawable.atom_ui_emoji_1f3eb);
        sSoftbanksMap.put(57688, R.drawable.atom_ui_emoji_1f3e8);
        sSoftbanksMap.put(57689, R.drawable.atom_ui_emoji_1f68c);
        sSoftbanksMap.put(57690, R.drawable.atom_ui_emoji_1f695);
        sSoftbanksMap.put(57857, R.drawable.atom_ui_emoji_1f6b6);
        sSoftbanksMap.put(57858, R.drawable.atom_ui_emoji_1f6a2);
        sSoftbanksMap.put(57859, R.drawable.atom_ui_emoji_1f201);
        sSoftbanksMap.put(57860, R.drawable.atom_ui_emoji_1f49f);
        sSoftbanksMap.put(57861, R.drawable.atom_ui_emoji_2734);
        sSoftbanksMap.put(57862, R.drawable.atom_ui_emoji_2733);
        sSoftbanksMap.put(57863, R.drawable.atom_ui_emoji_1f51e);
        sSoftbanksMap.put(57864, R.drawable.atom_ui_emoji_1f6ad);
        sSoftbanksMap.put(57865, R.drawable.atom_ui_emoji_1f530);
        sSoftbanksMap.put(57866, R.drawable.atom_ui_emoji_267f);
        sSoftbanksMap.put(57867, R.drawable.atom_ui_emoji_1f4f6);
        sSoftbanksMap.put(57868, R.drawable.atom_ui_emoji_2665);
        sSoftbanksMap.put(57869, R.drawable.atom_ui_emoji_2666);
        sSoftbanksMap.put(57870, R.drawable.atom_ui_emoji_2660);
        sSoftbanksMap.put(57871, R.drawable.atom_ui_emoji_2663);
        sSoftbanksMap.put(57872, R.drawable.atom_ui_emoji_0023);
        sSoftbanksMap.put(57873, R.drawable.atom_ui_emoji_27bf);
        sSoftbanksMap.put(57874, R.drawable.atom_ui_emoji_1f195);
        sSoftbanksMap.put(57875, R.drawable.atom_ui_emoji_1f199);
        sSoftbanksMap.put(57876, R.drawable.atom_ui_emoji_1f192);
        sSoftbanksMap.put(57877, R.drawable.atom_ui_emoji_1f236);
        sSoftbanksMap.put(57878, R.drawable.atom_ui_emoji_1f21a);
        sSoftbanksMap.put(57879, R.drawable.atom_ui_emoji_1f237);
        sSoftbanksMap.put(57880, R.drawable.atom_ui_emoji_1f238);
        sSoftbanksMap.put(57881, R.drawable.atom_ui_emoji_1f534);
        sSoftbanksMap.put(57882, R.drawable.atom_ui_emoji_1f532);
        sSoftbanksMap.put(57883, R.drawable.atom_ui_emoji_1f533);
        sSoftbanksMap.put(57884, R.drawable.atom_ui_emoji_0031);
        sSoftbanksMap.put(57885, R.drawable.atom_ui_emoji_0032);
        sSoftbanksMap.put(57886, R.drawable.atom_ui_emoji_0033);
        sSoftbanksMap.put(57887, R.drawable.atom_ui_emoji_0034);
        sSoftbanksMap.put(57888, R.drawable.atom_ui_emoji_0035);
        sSoftbanksMap.put(57889, R.drawable.atom_ui_emoji_0036);
        sSoftbanksMap.put(57890, R.drawable.atom_ui_emoji_0037);
        sSoftbanksMap.put(57891, R.drawable.atom_ui_emoji_0038);
        sSoftbanksMap.put(57892, R.drawable.atom_ui_emoji_0039);
        sSoftbanksMap.put(57893, R.drawable.atom_ui_emoji_0030);
        sSoftbanksMap.put(57894, R.drawable.atom_ui_emoji_1f250);
        sSoftbanksMap.put(57895, R.drawable.atom_ui_emoji_1f239);
        sSoftbanksMap.put(57896, R.drawable.atom_ui_emoji_1f202);
        sSoftbanksMap.put(57897, R.drawable.atom_ui_emoji_1f194);
        sSoftbanksMap.put(57898, R.drawable.atom_ui_emoji_1f235);
        sSoftbanksMap.put(57899, R.drawable.atom_ui_emoji_1f233);
        sSoftbanksMap.put(57900, R.drawable.atom_ui_emoji_1f22f);
        sSoftbanksMap.put(57901, R.drawable.atom_ui_emoji_1f23a);
        sSoftbanksMap.put(57902, R.drawable.atom_ui_emoji_1f446);
        sSoftbanksMap.put(57903, R.drawable.atom_ui_emoji_1f447);
        sSoftbanksMap.put(57904, R.drawable.atom_ui_emoji_1f448);
        sSoftbanksMap.put(57905, R.drawable.atom_ui_emoji_1f449);
        sSoftbanksMap.put(57906, R.drawable.atom_ui_emoji_2b06);
        sSoftbanksMap.put(57907, R.drawable.atom_ui_emoji_2b07);
        sSoftbanksMap.put(57908, R.drawable.atom_ui_emoji_27a1);
        sSoftbanksMap.put(57909, R.drawable.atom_ui_emoji_1f519);
        sSoftbanksMap.put(57910, R.drawable.atom_ui_emoji_2197);
        sSoftbanksMap.put(57911, R.drawable.atom_ui_emoji_2196);
        sSoftbanksMap.put(57912, R.drawable.atom_ui_emoji_2198);
        sSoftbanksMap.put(57913, R.drawable.atom_ui_emoji_2199);
        sSoftbanksMap.put(57914, R.drawable.atom_ui_emoji_25b6);
        sSoftbanksMap.put(57915, R.drawable.atom_ui_emoji_25c0);
        sSoftbanksMap.put(57916, R.drawable.atom_ui_emoji_23e9);
        sSoftbanksMap.put(57917, R.drawable.atom_ui_emoji_23ea);
        sSoftbanksMap.put(57918, R.drawable.atom_ui_emoji_1f52e);
        sSoftbanksMap.put(57919, R.drawable.atom_ui_emoji_2648);
        sSoftbanksMap.put(57920, R.drawable.atom_ui_emoji_2649);
        sSoftbanksMap.put(57921, R.drawable.atom_ui_emoji_264a);
        sSoftbanksMap.put(57922, R.drawable.atom_ui_emoji_264b);
        sSoftbanksMap.put(57923, R.drawable.atom_ui_emoji_264c);
        sSoftbanksMap.put(57924, R.drawable.atom_ui_emoji_264d);
        sSoftbanksMap.put(57925, R.drawable.atom_ui_emoji_264e);
        sSoftbanksMap.put(57926, R.drawable.atom_ui_emoji_264f);
        sSoftbanksMap.put(57927, R.drawable.atom_ui_emoji_2650);
        sSoftbanksMap.put(57928, R.drawable.atom_ui_emoji_2651);
        sSoftbanksMap.put(57929, R.drawable.atom_ui_emoji_2652);
        sSoftbanksMap.put(57930, R.drawable.atom_ui_emoji_2653);
        sSoftbanksMap.put(57931, R.drawable.atom_ui_emoji_26ce);
        sSoftbanksMap.put(57932, R.drawable.atom_ui_emoji_1f51d);
        sSoftbanksMap.put(57933, R.drawable.atom_ui_emoji_1f197);
        sSoftbanksMap.put(57934, R.drawable.atom_ui_emoji_00a9);
        sSoftbanksMap.put(57935, R.drawable.atom_ui_emoji_00ae);
        sSoftbanksMap.put(57936, R.drawable.atom_ui_emoji_1f4f3);
        sSoftbanksMap.put(57937, R.drawable.atom_ui_emoji_1f4f4);
        sSoftbanksMap.put(57938, R.drawable.atom_ui_emoji_26a0);
        sSoftbanksMap.put(57939, R.drawable.atom_ui_emoji_1f481);
        sSoftbanksMap.put(58113, R.drawable.atom_ui_emoji_1f4c3);
        sSoftbanksMap.put(58114, R.drawable.atom_ui_emoji_1f454);
        sSoftbanksMap.put(58115, R.drawable.atom_ui_emoji_1f33a);
        sSoftbanksMap.put(58116, R.drawable.atom_ui_emoji_1f337);
        sSoftbanksMap.put(58117, R.drawable.atom_ui_emoji_1f33b);
        sSoftbanksMap.put(58118, R.drawable.atom_ui_emoji_1f490);
        sSoftbanksMap.put(58119, R.drawable.atom_ui_emoji_1f334);
        sSoftbanksMap.put(58120, R.drawable.atom_ui_emoji_1f335);
        sSoftbanksMap.put(58121, R.drawable.atom_ui_emoji_1f6be);
        sSoftbanksMap.put(58122, R.drawable.atom_ui_emoji_1f3a7);
        sSoftbanksMap.put(58123, R.drawable.atom_ui_emoji_1f376);
        sSoftbanksMap.put(58124, R.drawable.atom_ui_emoji_1f37b);
        sSoftbanksMap.put(58125, R.drawable.atom_ui_emoji_3297);
        sSoftbanksMap.put(58126, R.drawable.atom_ui_emoji_1f6ac);
        sSoftbanksMap.put(58127, R.drawable.atom_ui_emoji_1f48a);
        sSoftbanksMap.put(58128, R.drawable.atom_ui_emoji_1f388);
        sSoftbanksMap.put(58129, R.drawable.atom_ui_emoji_1f4a3);
        sSoftbanksMap.put(58130, R.drawable.atom_ui_emoji_1f389);
        sSoftbanksMap.put(58131, R.drawable.atom_ui_emoji_2702);
        sSoftbanksMap.put(58132, R.drawable.atom_ui_emoji_1f380);
        sSoftbanksMap.put(58133, R.drawable.atom_ui_emoji_3299);
        sSoftbanksMap.put(58134, R.drawable.atom_ui_emoji_1f4bd);
        sSoftbanksMap.put(58135, R.drawable.atom_ui_emoji_1f4e3);
        sSoftbanksMap.put(58136, R.drawable.atom_ui_emoji_1f452);
        sSoftbanksMap.put(58137, R.drawable.atom_ui_emoji_1f457);
        sSoftbanksMap.put(58138, R.drawable.atom_ui_emoji_1f461);
        sSoftbanksMap.put(58139, R.drawable.atom_ui_emoji_1f462);
        sSoftbanksMap.put(58140, R.drawable.atom_ui_emoji_1f484);
        sSoftbanksMap.put(58141, R.drawable.atom_ui_emoji_1f485);
        sSoftbanksMap.put(58142, R.drawable.atom_ui_emoji_1f486);
        sSoftbanksMap.put(58143, R.drawable.atom_ui_emoji_1f487);
        sSoftbanksMap.put(58144, R.drawable.atom_ui_emoji_1f488);
        sSoftbanksMap.put(58145, R.drawable.atom_ui_emoji_1f458);
        sSoftbanksMap.put(58146, R.drawable.atom_ui_emoji_1f459);
        sSoftbanksMap.put(58147, R.drawable.atom_ui_emoji_1f45c);
        sSoftbanksMap.put(58148, R.drawable.atom_ui_emoji_1f3ac);
        sSoftbanksMap.put(58149, R.drawable.atom_ui_emoji_1f514);
        sSoftbanksMap.put(58150, R.drawable.atom_ui_emoji_1f3b6);
        sSoftbanksMap.put(58151, R.drawable.atom_ui_emoji_1f493);
        sSoftbanksMap.put(58152, R.drawable.atom_ui_emoji_1f48c);
        sSoftbanksMap.put(58153, R.drawable.atom_ui_emoji_1f498);
        sSoftbanksMap.put(58154, R.drawable.atom_ui_emoji_1f499);
        sSoftbanksMap.put(58155, R.drawable.atom_ui_emoji_1f49a);
        sSoftbanksMap.put(58156, R.drawable.atom_ui_emoji_1f49b);
        sSoftbanksMap.put(58157, R.drawable.atom_ui_emoji_1f49c);
        sSoftbanksMap.put(58158, R.drawable.atom_ui_emoji_2728);
        sSoftbanksMap.put(58159, R.drawable.atom_ui_emoji_2b50);
        sSoftbanksMap.put(58160, R.drawable.atom_ui_emoji_1f4a8);
        sSoftbanksMap.put(58161, R.drawable.atom_ui_emoji_1f4a6);
        sSoftbanksMap.put(58162, R.drawable.atom_ui_emoji_2b55);
        sSoftbanksMap.put(58163, R.drawable.atom_ui_emoji_2716);
        sSoftbanksMap.put(58164, R.drawable.atom_ui_emoji_1f4a2);
        sSoftbanksMap.put(58165, R.drawable.atom_ui_emoji_1f31f);
        sSoftbanksMap.put(58166, R.drawable.atom_ui_emoji_2754);
        sSoftbanksMap.put(58167, R.drawable.atom_ui_emoji_2755);
        sSoftbanksMap.put(58168, R.drawable.atom_ui_emoji_1f375);
        sSoftbanksMap.put(58169, R.drawable.atom_ui_emoji_1f35e);
        sSoftbanksMap.put(58170, R.drawable.atom_ui_emoji_1f366);
        sSoftbanksMap.put(58171, R.drawable.atom_ui_emoji_1f35f);
        sSoftbanksMap.put(58172, R.drawable.atom_ui_emoji_1f361);
        sSoftbanksMap.put(58173, R.drawable.atom_ui_emoji_1f358);
        sSoftbanksMap.put(58174, R.drawable.atom_ui_emoji_1f35a);
        sSoftbanksMap.put(58175, R.drawable.atom_ui_emoji_1f35d);
        sSoftbanksMap.put(58176, R.drawable.atom_ui_emoji_1f35c);
        sSoftbanksMap.put(58177, R.drawable.atom_ui_emoji_1f35b);
        sSoftbanksMap.put(58178, R.drawable.atom_ui_emoji_1f359);
        sSoftbanksMap.put(58179, R.drawable.atom_ui_emoji_1f362);
        sSoftbanksMap.put(58180, R.drawable.atom_ui_emoji_1f363);
        sSoftbanksMap.put(58181, R.drawable.atom_ui_emoji_1f34e);
        sSoftbanksMap.put(58182, R.drawable.atom_ui_emoji_1f34a);
        sSoftbanksMap.put(58183, R.drawable.atom_ui_emoji_1f353);
        sSoftbanksMap.put(58184, R.drawable.atom_ui_emoji_1f349);
        sSoftbanksMap.put(58185, R.drawable.atom_ui_emoji_1f345);
        sSoftbanksMap.put(58186, R.drawable.atom_ui_emoji_1f346);
        sSoftbanksMap.put(58187, R.drawable.atom_ui_emoji_1f382);
        sSoftbanksMap.put(58188, R.drawable.atom_ui_emoji_1f371);
        sSoftbanksMap.put(58189, R.drawable.atom_ui_emoji_1f372);
        sSoftbanksMap.put(58369, R.drawable.atom_ui_emoji_1f625);
        sSoftbanksMap.put(58370, R.drawable.atom_ui_emoji_1f60f);
        sSoftbanksMap.put(58371, R.drawable.atom_ui_emoji_1f614);
        sSoftbanksMap.put(58372, R.drawable.atom_ui_emoji_1f601);
        sSoftbanksMap.put(58373, R.drawable.atom_ui_emoji_1f609);
        sSoftbanksMap.put(58374, R.drawable.atom_ui_emoji_1f623);
        sSoftbanksMap.put(58375, R.drawable.atom_ui_emoji_1f616);
        sSoftbanksMap.put(58376, R.drawable.atom_ui_emoji_1f62a);
        sSoftbanksMap.put(58377, R.drawable.atom_ui_emoji_1f445);
        sSoftbanksMap.put(58378, R.drawable.atom_ui_emoji_1f606);
        sSoftbanksMap.put(58379, R.drawable.atom_ui_emoji_1f628);
        sSoftbanksMap.put(58380, R.drawable.atom_ui_emoji_1f637);
        sSoftbanksMap.put(58381, R.drawable.atom_ui_emoji_1f633);
        sSoftbanksMap.put(58382, R.drawable.atom_ui_emoji_1f612);
        sSoftbanksMap.put(58383, R.drawable.atom_ui_emoji_1f630);
        sSoftbanksMap.put(58384, R.drawable.atom_ui_emoji_1f632);
        sSoftbanksMap.put(58385, R.drawable.atom_ui_emoji_1f62d);
        sSoftbanksMap.put(58386, R.drawable.atom_ui_emoji_1f602);
        sSoftbanksMap.put(58387, R.drawable.atom_ui_emoji_1f622);
        sSoftbanksMap.put(58388, R.drawable.atom_ui_emoji_263a);
        sSoftbanksMap.put(58389, R.drawable.atom_ui_emoji_1f605);
        sSoftbanksMap.put(58390, R.drawable.atom_ui_emoji_1f621);
        sSoftbanksMap.put(58391, R.drawable.atom_ui_emoji_1f61a);
        sSoftbanksMap.put(58392, R.drawable.atom_ui_emoji_1f618);
        sSoftbanksMap.put(58393, R.drawable.atom_ui_emoji_1f440);
        sSoftbanksMap.put(58394, R.drawable.atom_ui_emoji_1f443);
        sSoftbanksMap.put(58395, R.drawable.atom_ui_emoji_1f442);
        sSoftbanksMap.put(58396, R.drawable.atom_ui_emoji_1f444);
        sSoftbanksMap.put(58397, R.drawable.atom_ui_emoji_1f64f);
        sSoftbanksMap.put(58398, R.drawable.atom_ui_emoji_1f44b);
        sSoftbanksMap.put(58399, R.drawable.atom_ui_emoji_1f44f);
        sSoftbanksMap.put(58400, R.drawable.atom_ui_emoji_1f44c);
        sSoftbanksMap.put(58401, R.drawable.atom_ui_emoji_1f44e);
        sSoftbanksMap.put(58402, R.drawable.atom_ui_emoji_1f450);
        sSoftbanksMap.put(58403, R.drawable.atom_ui_emoji_1f645);
        sSoftbanksMap.put(58404, R.drawable.atom_ui_emoji_1f646);
        sSoftbanksMap.put(58405, R.drawable.atom_ui_emoji_1f491);
        sSoftbanksMap.put(58406, R.drawable.atom_ui_emoji_1f647);
        sSoftbanksMap.put(58407, R.drawable.atom_ui_emoji_1f64c);
        sSoftbanksMap.put(58408, R.drawable.atom_ui_emoji_1f46b);
        sSoftbanksMap.put(58409, R.drawable.atom_ui_emoji_1f46f);
        sSoftbanksMap.put(58410, R.drawable.atom_ui_emoji_1f3c0);
        sSoftbanksMap.put(58411, R.drawable.atom_ui_emoji_1f3c8);
        sSoftbanksMap.put(58412, R.drawable.atom_ui_emoji_1f3b1);
        sSoftbanksMap.put(58413, R.drawable.atom_ui_emoji_1f3ca);
        sSoftbanksMap.put(58414, R.drawable.atom_ui_emoji_1f699);
        sSoftbanksMap.put(58415, R.drawable.atom_ui_emoji_1f69a);
        sSoftbanksMap.put(58416, R.drawable.atom_ui_emoji_1f692);
        sSoftbanksMap.put(58417, R.drawable.atom_ui_emoji_1f691);
        sSoftbanksMap.put(58418, R.drawable.atom_ui_emoji_1f693);
        sSoftbanksMap.put(58419, R.drawable.atom_ui_emoji_1f3a2);
        sSoftbanksMap.put(58420, R.drawable.atom_ui_emoji_1f687);
        sSoftbanksMap.put(58421, R.drawable.atom_ui_emoji_1f684);
        sSoftbanksMap.put(58422, R.drawable.atom_ui_emoji_1f38d);
        sSoftbanksMap.put(58423, R.drawable.atom_ui_emoji_1f49d);
        sSoftbanksMap.put(58424, R.drawable.atom_ui_emoji_1f38e);
        sSoftbanksMap.put(58425, R.drawable.atom_ui_emoji_1f393);
        sSoftbanksMap.put(58426, R.drawable.atom_ui_emoji_1f392);
        sSoftbanksMap.put(58427, R.drawable.atom_ui_emoji_1f38f);
        sSoftbanksMap.put(58428, R.drawable.atom_ui_emoji_1f302);
        sSoftbanksMap.put(58429, R.drawable.atom_ui_emoji_1f492);
        sSoftbanksMap.put(58430, R.drawable.atom_ui_emoji_1f30a);
        sSoftbanksMap.put(58431, R.drawable.atom_ui_emoji_1f367);
        sSoftbanksMap.put(58432, R.drawable.atom_ui_emoji_1f387);
        sSoftbanksMap.put(58433, R.drawable.atom_ui_emoji_1f41a);
        sSoftbanksMap.put(58434, R.drawable.atom_ui_emoji_1f390);
        sSoftbanksMap.put(58435, R.drawable.atom_ui_emoji_1f300);
        sSoftbanksMap.put(58436, R.drawable.atom_ui_emoji_1f33e);
        sSoftbanksMap.put(58437, R.drawable.atom_ui_emoji_1f383);
        sSoftbanksMap.put(58438, R.drawable.atom_ui_emoji_1f391);
        sSoftbanksMap.put(58439, R.drawable.atom_ui_emoji_1f343);
        sSoftbanksMap.put(58440, R.drawable.atom_ui_emoji_1f385);
        sSoftbanksMap.put(58441, R.drawable.atom_ui_emoji_1f305);
        sSoftbanksMap.put(58442, R.drawable.atom_ui_emoji_1f307);
        sSoftbanksMap.put(58443, R.drawable.atom_ui_emoji_1f303);
        sSoftbanksMap.put(58443, R.drawable.atom_ui_emoji_1f30c);
        sSoftbanksMap.put(58444, R.drawable.atom_ui_emoji_1f308);
        sSoftbanksMap.put(58625, R.drawable.atom_ui_emoji_1f3e9);
        sSoftbanksMap.put(58626, R.drawable.atom_ui_emoji_1f3a8);
        sSoftbanksMap.put(58627, R.drawable.atom_ui_emoji_1f3a9);
        sSoftbanksMap.put(58628, R.drawable.atom_ui_emoji_1f3ec);
        sSoftbanksMap.put(58629, R.drawable.atom_ui_emoji_1f3ef);
        sSoftbanksMap.put(58630, R.drawable.atom_ui_emoji_1f3f0);
        sSoftbanksMap.put(58631, R.drawable.atom_ui_emoji_1f3a6);
        sSoftbanksMap.put(58632, R.drawable.atom_ui_emoji_1f3ed);
        sSoftbanksMap.put(58633, R.drawable.atom_ui_emoji_1f5fc);
        sSoftbanksMap.put(58635, R.drawable.atom_ui_emoji_1f1ef_1f1f5);
        sSoftbanksMap.put(58636, R.drawable.atom_ui_emoji_1f1fa_1f1f8);
        sSoftbanksMap.put(58637, R.drawable.atom_ui_emoji_1f1eb_1f1f7);
        sSoftbanksMap.put(58638, R.drawable.atom_ui_emoji_1f1e9_1f1ea);
        sSoftbanksMap.put(58639, R.drawable.atom_ui_emoji_1f1ee_1f1f9);
        sSoftbanksMap.put(58640, R.drawable.atom_ui_emoji_1f1ec_1f1e7);
        sSoftbanksMap.put(58641, R.drawable.atom_ui_emoji_1f1ea_1f1f8);
        sSoftbanksMap.put(58642, R.drawable.atom_ui_emoji_1f1f7_1f1fa);
        sSoftbanksMap.put(58643, R.drawable.atom_ui_emoji_1f1e8_1f1f3);
        sSoftbanksMap.put(58644, R.drawable.atom_ui_emoji_1f1f0_1f1f7);
        sSoftbanksMap.put(58645, R.drawable.atom_ui_emoji_1f471);
        sSoftbanksMap.put(58646, R.drawable.atom_ui_emoji_1f472);
        sSoftbanksMap.put(58647, R.drawable.atom_ui_emoji_1f473);
        sSoftbanksMap.put(58648, R.drawable.atom_ui_emoji_1f474);
        sSoftbanksMap.put(58649, R.drawable.atom_ui_emoji_1f475);
        sSoftbanksMap.put(58650, R.drawable.atom_ui_emoji_1f476);
        sSoftbanksMap.put(58651, R.drawable.atom_ui_emoji_1f477);
        sSoftbanksMap.put(58652, R.drawable.atom_ui_emoji_1f478);
        sSoftbanksMap.put(58653, R.drawable.atom_ui_emoji_1f5fd);
        sSoftbanksMap.put(58654, R.drawable.atom_ui_emoji_1f482);
        sSoftbanksMap.put(58655, R.drawable.atom_ui_emoji_1f483);
        sSoftbanksMap.put(58656, R.drawable.atom_ui_emoji_1f42c);
        sSoftbanksMap.put(58657, R.drawable.atom_ui_emoji_1f426);
        sSoftbanksMap.put(58658, R.drawable.atom_ui_emoji_1f420);
        sSoftbanksMap.put(58659, R.drawable.atom_ui_emoji_1f423);
        sSoftbanksMap.put(58660, R.drawable.atom_ui_emoji_1f439);
        sSoftbanksMap.put(58661, R.drawable.atom_ui_emoji_1f41b);
        sSoftbanksMap.put(58662, R.drawable.atom_ui_emoji_1f418);
        sSoftbanksMap.put(58663, R.drawable.atom_ui_emoji_1f428);
        sSoftbanksMap.put(58664, R.drawable.atom_ui_emoji_1f412);
        sSoftbanksMap.put(58665, R.drawable.atom_ui_emoji_1f411);
        sSoftbanksMap.put(58666, R.drawable.atom_ui_emoji_1f43a);
        sSoftbanksMap.put(58667, R.drawable.atom_ui_emoji_1f42e);
        sSoftbanksMap.put(58668, R.drawable.atom_ui_emoji_1f430);
        sSoftbanksMap.put(58669, R.drawable.atom_ui_emoji_1f40d);
        sSoftbanksMap.put(58670, R.drawable.atom_ui_emoji_1f414);
        sSoftbanksMap.put(58671, R.drawable.atom_ui_emoji_1f417);
        sSoftbanksMap.put(58672, R.drawable.atom_ui_emoji_1f42b);
        sSoftbanksMap.put(58673, R.drawable.atom_ui_emoji_1f438);
        sSoftbanksMap.put(58674, R.drawable.atom_ui_emoji_1f170);
        sSoftbanksMap.put(58675, R.drawable.atom_ui_emoji_1f171);
        sSoftbanksMap.put(58676, R.drawable.atom_ui_emoji_1f18e);
        sSoftbanksMap.put(58677, R.drawable.atom_ui_emoji_1f17e);
        sSoftbanksMap.put(58678, R.drawable.atom_ui_emoji_1f43e);
        sSoftbanksMap.put(58679, R.drawable.atom_ui_emoji_2122);
    }

    private static boolean isSoftBankEmoji(char c) {
        return (c >> 12) == 14;
    }

    private static int getEmojiResource(Context context, int codePoint) {
        return sEmojisMap.get(codePoint);
    }

    private static int getSoftbankEmojiResource(char c) {
        return sSoftbanksMap.get(c);
    }

    public static void addEmojis(Context context, Spannable text, int emojiSize, int textSize) {
        addEmojis(context, text, emojiSize, textSize, 0, -1, false);
    }

    public static void addEmojis(Context context, Spannable text, int emojiSize, int textSize, int index, int length) {
        addEmojis(context, text, emojiSize, textSize, index, length, false);
    }

    public static void addEmojis(Context context, Spannable text, int emojiSize, int textSize, boolean useSystemDefault) {
        addEmojis(context, text, emojiSize, textSize, 0, -1, useSystemDefault);
    }

    public static void addEmojis(Context context, Spannable text, int emojiSize, int textSize, int index, int length, boolean useSystemDefault) {
        if (!useSystemDefault) {
            int textLength = text.length();
            int textLengthToProcess = (length < 0 || length >= textLength - index) ? textLength : length + index;
            EmojiconSpan[] oldSpans = (EmojiconSpan[]) text.getSpans(0, textLength, EmojiconSpan.class);
            for (Object removeSpan : oldSpans) {
                text.removeSpan(removeSpan);
            }
            int i = index;
            while (i < textLengthToProcess) {
                int skip = 0;
                int icon = 0;
                char c = text.charAt(i);
                if (isSoftBankEmoji(c)) {
                    icon = getSoftbankEmojiResource(c);
                    skip = icon == 0 ? 0 : 1;
                }
                if (icon == 0) {
                    int unicode = Character.codePointAt(text, i);
                    skip = Character.charCount(unicode);
                    if (unicode > 255) {
                        icon = getEmojiResource(context, unicode);
                    }
                    if (icon == 0 && i + skip < textLengthToProcess) {
                        int followUnicode = Character.codePointAt(text, i + skip);
                        int followSkip;
                        if (followUnicode == 8419) {
                            followSkip = Character.charCount(followUnicode);
                            switch (unicode) {
                                case 35:
                                    icon = R.drawable.atom_ui_emoji_0023;
                                    break;
                                case 48:
                                    icon = R.drawable.atom_ui_emoji_0030;
                                    break;
                                case 49:
                                    icon = R.drawable.atom_ui_emoji_0031;
                                    break;
                                case 50:
                                    icon = R.drawable.atom_ui_emoji_0032;
                                    break;
                                case 51:
                                    icon = R.drawable.atom_ui_emoji_0033;
                                    break;
                                case 52:
                                    icon = R.drawable.atom_ui_emoji_0034;
                                    break;
                                case 53:
                                    icon = R.drawable.atom_ui_emoji_0035;
                                    break;
                                case 54:
                                    icon = R.drawable.atom_ui_emoji_0036;
                                    break;
                                case 55:
                                    icon = R.drawable.atom_ui_emoji_0037;
                                    break;
                                case 56:
                                    icon = R.drawable.atom_ui_emoji_0038;
                                    break;
                                case 57:
                                    icon = R.drawable.atom_ui_emoji_0039;
                                    break;
                                default:
                                    followSkip = 0;
                                    break;
                            }
                            skip += followSkip;
                        } else {
                            followSkip = Character.charCount(followUnicode);
                            switch (unicode) {
                                case 127464:
                                    if (followUnicode != 127475) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1e8_1f1f3;
                                        break;
                                    }
                                case 127465:
                                    if (followUnicode != 127466) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1e9_1f1ea;
                                        break;
                                    }
                                case 127466:
                                    if (followUnicode != 127480) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1ea_1f1f8;
                                        break;
                                    }
                                case 127467:
                                    if (followUnicode != 127479) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1eb_1f1f7;
                                        break;
                                    }
                                case 127468:
                                    if (followUnicode != 127463) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1ec_1f1e7;
                                        break;
                                    }
                                case 127470:
                                    if (followUnicode != 127481) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1ee_1f1f9;
                                        break;
                                    }
                                case 127471:
                                    if (followUnicode != 127477) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1ef_1f1f5;
                                        break;
                                    }
                                case 127472:
                                    if (followUnicode != 127479) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1f0_1f1f7;
                                        break;
                                    }
                                case 127479:
                                    if (followUnicode != 127482) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1f7_1f1fa;
                                        break;
                                    }
                                case 127482:
                                    if (followUnicode != 127480) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1fa_1f1f8;
                                        break;
                                    }
                                default:
                                    followSkip = 0;
                                    break;
                            }
                            skip += followSkip;
                        }
                    }
                }
                if (icon > 0) {
                    text.setSpan(new EmojiconSpan(context, icon, emojiSize, textSize), i, i + skip, 33);
                }
                i += skip;
            }
        }
    }

    public static void addEmojisForEdit(Context context, Spannable text, int emojiSize, int textSize, int index, int length, boolean useSystemDefault) {
        if (!useSystemDefault) {
            int textLength = text.length();
            int textLengthToProcess = (length < 0 || length >= textLength - index) ? textLength : length + index;
            for (EmojiconSpan oldSpan : (EmojiconSpan[]) text.getSpans(0, textLength, EmojiconSpan.class)) {
                text.removeSpan(oldSpan);
            }
            int pre = 0;
            boolean isMyEmoji = false;
            boolean keyStart = false;
            StringBuilder builder = new StringBuilder(40);
            StringBuilder stringBuilder = new StringBuilder(40);
            int i = index;
            while (i < textLengthToProcess) {
                int skip = 0;
                int icon = 0;
                char c = text.charAt(i);
                if (isSoftBankEmoji(c)) {
                    icon = getSoftbankEmojiResource(c);
                    skip = icon == 0 ? 0 : 1;
                }
                if (icon == 0) {
                    int unicode = Character.codePointAt(text, i);
                    skip = Character.charCount(unicode);
                    if (unicode > 255) {
                        icon = getEmojiResource(context, unicode);
                    }
                    if (icon == 0 && i + skip < textLengthToProcess) {
                        int followUnicode = Character.codePointAt(text, i + skip);
                        int followSkip;
                        if (followUnicode == 8419) {
                            followSkip = Character.charCount(followUnicode);
                            switch (unicode) {
                                case 35:
                                    icon = R.drawable.atom_ui_emoji_0023;
                                    break;
                                case 48:
                                    icon = R.drawable.atom_ui_emoji_0030;
                                    break;
                                case 49:
                                    icon = R.drawable.atom_ui_emoji_0031;
                                    break;
                                case 50:
                                    icon = R.drawable.atom_ui_emoji_0032;
                                    break;
                                case 51:
                                    icon = R.drawable.atom_ui_emoji_0033;
                                    break;
                                case 52:
                                    icon = R.drawable.atom_ui_emoji_0034;
                                    break;
                                case 53:
                                    icon = R.drawable.atom_ui_emoji_0035;
                                    break;
                                case 54:
                                    icon = R.drawable.atom_ui_emoji_0036;
                                    break;
                                case 55:
                                    icon = R.drawable.atom_ui_emoji_0037;
                                    break;
                                case 56:
                                    icon = R.drawable.atom_ui_emoji_0038;
                                    break;
                                case 57:
                                    icon = R.drawable.atom_ui_emoji_0039;
                                    break;
                                default:
                                    followSkip = 0;
                                    break;
                            }
                            skip += followSkip;
                        } else {
                            followSkip = Character.charCount(followUnicode);
                            switch (unicode) {
                                case 127464:
                                    if (followUnicode != 127475) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1e8_1f1f3;
                                        break;
                                    }
                                case 127465:
                                    if (followUnicode != 127466) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1e9_1f1ea;
                                        break;
                                    }
                                case 127466:
                                    if (followUnicode != 127480) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1ea_1f1f8;
                                        break;
                                    }
                                case 127467:
                                    if (followUnicode != 127479) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1eb_1f1f7;
                                        break;
                                    }
                                case 127468:
                                    if (followUnicode != 127463) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1ec_1f1e7;
                                        break;
                                    }
                                case 127470:
                                    if (followUnicode != 127481) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1ee_1f1f9;
                                        break;
                                    }
                                case 127471:
                                    if (followUnicode != 127477) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1ef_1f1f5;
                                        break;
                                    }
                                case 127472:
                                    if (followUnicode != 127479) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1f0_1f1f7;
                                        break;
                                    }
                                case 127479:
                                    if (followUnicode != 127482) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1f7_1f1fa;
                                        break;
                                    }
                                case 127482:
                                    if (followUnicode != 127480) {
                                        icon = 0;
                                        break;
                                    } else {
                                        icon = R.drawable.atom_ui_emoji_1f1fa_1f1f8;
                                        break;
                                    }
                                default:
                                    followSkip = 0;
                                    break;
                            }
                            skip += followSkip;
                        }
                    }
                }
                if (icon > 0) {
                    isMyEmoji = false;
                    keyStart = false;
                    builder = new StringBuilder(40);
                    stringBuilder = new StringBuilder(40);
                    text.setSpan(new EmojiconSpan(context, icon, emojiSize, textSize), i, i + skip, 33);
                } else if (isMyEmoji) {
                    if (c == 1) {
                        keyStart = true;
                    } else {
                        if (c == 255 && builder.length() >= 2) {
                            EmoticonEntity entity = EmotionUtils.getEmoticionByShortCut(builder.toString(), pkgBuilder.toString(), true);
                            if (entity != null) {
                                InputStream is = null;
                                try {
                                    Bitmap bitmap;
                                    if (entity.fileFiexd.startsWith("emoticons/") || entity.fileFiexd.startsWith("Big_Emoticons/")) {
                                        is = context.getAssets().open(entity.fileFiexd);
                                        bitmap = BitmapFactory.decodeStream(is);
                                    } else {
                                        bitmap = BitmapHelper.decodeFile(entity.fileFiexd);
                                    }
                                    if (bitmap != null) {
                                        text.setSpan(new EmojiconSpan(context, new BitmapDrawable(bitmap), emojiSize, textSize), pre, i + 1, 33);
                                    }
                                    if (is != null) {
                                        try {
                                            is.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    if (is != null) {
                                        try {
                                            is.close();
                                        } catch (IOException e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                } catch (Throwable th) {
                                    if (is != null) {
                                        try {
                                            is.close();
                                        } catch (IOException e32) {
                                            e32.printStackTrace();
                                        }
                                    }
                                }
                            }
                            builder.setLength(0);
                            pkgBuilder.setLength(0);
                            isMyEmoji = false;
                            keyStart = false;
                        }
                        if (isMyEmoji && keyStart) {
                            builder.append(c);
                        } else if (isMyEmoji) {
                            pkgBuilder.append(c);
                        }
                        if (builder.length() >= 40 || pkgBuilder.length() >= 40) {
                            builder.setLength(0);
                            pkgBuilder.setLength(0);
                            isMyEmoji = false;
                            keyStart = false;
                        }
                    }
                } else if (c == 0) {
                    isMyEmoji = true;
                    pre = i;
                }
                i += skip;
            }
        }
    }
}
