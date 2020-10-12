package SatanParlor.characters;

import SatanParlor.relics.eagle;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import SatanParlor.DefaultMod;
import SatanParlor.cards.*;
import SatanParlor.relics.DefaultClickableRelic;
import SatanParlor.relics.PlaceholderRelic;
import SatanParlor.relics.PlaceholderRelic2;
import org.omg.CORBA.ORB;

import java.util.ArrayList;

import static SatanParlor.DefaultMod.*;
import static SatanParlor.characters.Kratos.Enums.COLOR_RAGE;

public class Kratos extends CustomPlayer {
    public static final Logger logger = LogManager.getLogger(DefaultMod.class.getName());


    public static class Enums{
        @SpireEnum
        public static AbstractPlayer.PlayerClass KRATOS;
        @SpireEnum(name = "KRATOS_COLOR")
        public static AbstractCard.CardColor COLOR_RAGE;
        @SpireEnum(name = "KRATOS_COLOR") @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    // =============== BASE STATS =================

    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 50;
    public static final int MAX_HP = 60;
    public static final int STARTING_GOLD = 129;
    public static final int CARD_DRAW = 5;
    public static final int ORB_SLOTS = 0;

    // =============== STRINGS =================

    private static final String ID = makeID("KratosCharacter");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    public static final String[] orbTextures = {
            "SatanParlorResources/images/char/kratos/orb/layer1.png",
            "SatanParlorResources/images/char/kratos/orb/layer2.png",
            "SatanParlorResources/images/char/kratos/orb/layer3.png",
            "SatanParlorResources/images/char/kratos/orb/layer4.png",
            "SatanParlorResources/images/char/kratos/orb/layer5.png",
            "SatanParlorResources/images/char/kratos/orb/layer6.png",
            "SatanParlorResources/images/char/kratos/orb/layer1d.png",
            "SatanParlorResources/images/char/kratos/orb/layer2d.png",
            "SatanParlorResources/images/char/kratos/orb/layer3d.png",
            "SatanParlorResources/images/char/kratos/orb/layer4d.png",
            "SatanParlorResources/images/char/kratos/orb/layer5d.png",};


    public Kratos(String name, PlayerClass setClass){
        super(name, setClass, orbTextures,
                "SatanParlorResources/images/char/kratos/orb/vfx.png", null,
                new SpriterAnimation(
                        "SatanParlorResources/images/char/kratos/Spriter/KratosAnimation.scml"));

        // =============== TEXTURES, ENERGY, LOADOUT =================

        initializeClass(null, // required call to load textures and setup energy/loadout.
                // I left these in DefaultMod.java (Ctrl+click them to see where they are, Ctrl+hover to see what they read.)
                THE_DEFAULT_SHOULDER_2, // campfire pose
                THE_DEFAULT_SHOULDER_1, // another campfire pose
                THE_DEFAULT_CORPSE, // dead corpse
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN)); // energy manager

        // =============== /TEXTURES, ENERGY, LOADOUT/ =================


        // =============== ANIMATIONS =================

        loadAnimation(
                THE_DEFAULT_SKELETON_ATLAS,
                THE_DEFAULT_SKELETON_JSON,
                1.0f);
        AnimationState.TrackEntry e = state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());

        // =============== /ANIMATIONS/ =================


        // =============== TEXT BUBBLE LOCATION =================

        dialogX = (drawX + 0.0F * Settings.scale); // set location for text bubbles
        dialogY = (drawY + 220.0F * Settings.scale); // you can just copy these values

        // =============== /TEXT BUBBLE LOCATION/ =================
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0], STARTING_HP, MAX_HP,
                ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }


    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();

        logger.info("Begin loading Kratos starter deck strings");
        // 4 strikes
        retVal.add(Strike_K.ID);
        retVal.add(Strike_K.ID);
        retVal.add(Strike_K.ID);
        retVal.add(Strike_K.ID);
        // 4 defends
        retVal.add(Defend_K.ID);
        retVal.add(Defend_K.ID);
        retVal.add(Defend_K.ID);
        retVal.add(Defend_K.ID);
        // 1 cyclone
        retVal.add(cyclone.ID);
        // 1 curse
        retVal.add(AthenaBane.ID);
        return retVal;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();

        retVal.add(eagle.ID);
        UnlockTracker.markRelicAsSeen(eagle.ID);

        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_DAGGER_1", 1.25f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_DAGGER_1";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 3;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.COLOR_RAGE;
    }

    @Override
    public Color getCardTrailColor() {
        return KRATOS_RAGE;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new cyclone();
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new Kratos(name,chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return KRATOS_RAGE;
    }

    @Override
    public Color getSlashAttackColor() {
        return KRATOS_RAGE;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY
        };
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }
}
