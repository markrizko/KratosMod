package SatanParlor.events;

import SatanParlor.relics.chalice;
import basemod.devcommands.deck.Deck;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.colorless.Apotheosis;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import SatanParlor.DefaultMod;

import java.util.Iterator;

import static SatanParlor.DefaultMod.makeEventPath;

public class HerasGardenEvent extends AbstractImageEvent {


    public static final String ID = DefaultMod.makeID("HerasGardenEvent");
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);

    private static final String NAME = eventStrings.NAME;
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    public static final String IMG = makeEventPath("HerasGardenEvent.png");

    private int screenNum = 0; // The initial screen we will see when encountering the event - screen 0;

    public HerasGardenEvent() {
        super(NAME, DESCRIPTIONS[0]+DESCRIPTIONS[1], IMG);

        imageEventText.setDialogOption(OPTIONS[0]);
        imageEventText.setDialogOption(OPTIONS[4]);

        // The first dialogue options available to us.
        /*
        imageEventText.setDialogOption(OPTIONS[0]); // Inspiration - Gain a Random Starting Relic
        imageEventText.setDialogOption(OPTIONS[1] + healthdamage + OPTIONS[2]); // Denial - lose healthDamage Max HP
        imageEventText.setDialogOption(OPTIONS[3], new Apotheosis()); // Acceptance - Gain Apotheosis
        imageEventText.setDialogOption(OPTIONS[4]); // TOUCH THE MIRROR
        */
    }

    @Override
    protected void buttonEffect(int i) { // This is the event:
        switch (screenNum) {
            case 0: // While you are on screen number 0 (The starting screen)
                switch (i) {
                    case 0: // If you press button the first button (Button at index 0), in this case: Inspiration.
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2), new chalice());
                        imageEventText.loadImage("SatanParlorResources/images/events/HerasGardenEvent2.png");
                        this.imageEventText.updateBodyText(DESCRIPTIONS[2]);// Update the text of the event
                        this.imageEventText.updateDialogOption(0,OPTIONS[1]);
                        this.imageEventText.clearRemainingOptions(); // 2. and remove all others;
                        this.imageEventText.setDialogOption(OPTIONS[2]);
                        this.imageEventText.setDialogOption(OPTIONS[3]);

                        screenNum = 1; // Screen set the screen number to 1. Once we exit the switch (i) statement,
                        // we'll still continue the switch (screenNum) statement. It'll find screen 1 and do it's actions
                        // (in our case, that's the final screen, but you can chain as many as you want like that
                        break; // Onto screen 1 we go.
                    case 1: // If you press button the second button (Button at index 1), in this case: Deinal
                        openMap();
                        break;
                }
                break;
            case 1: // Welcome to screenNum = 1;
                switch (i) {
                    case 0: // If you press the first (and this should be the only) button,
                        // OBTAIN A RANDOM RARE CARD

                        AbstractCard rand = AbstractDungeon.getCard(AbstractCard.CardRarity.RARE, new Random());
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(rand, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        // AbstractDungeon.player.masterDeck.card

                        imageEventText.loadImage("SatanParlorResources/images/events/HerasGardenEvent3.png");
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]); // go to screen 2
                        this.imageEventText.updateDialogOption(0,OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 2;
                        break;
                    case 1:
                        // RANDOM BOSS RELIC
                        AbstractRelic randomRelic = RelicLibrary.bossList.get(AbstractDungeon.relicRng.random(RelicLibrary.bossList.size()-1)).makeCopy();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float) (Settings.WIDTH / 2), (float) (Settings.HEIGHT / 2), randomRelic);

                        imageEventText.loadImage("SatanParlorResources/images/events/HerasGardenEvent3.png");
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]); // go to screen 2
                        this.imageEventText.updateDialogOption(0,OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 2;
                        break;
                    case 2:
                        // GAIN 250 GOLD
                        AbstractDungeon.player.gainGold(150);

                        imageEventText.loadImage("SatanParlorResources/images/events/HerasGardenEvent3.png");
                        this.imageEventText.updateBodyText(DESCRIPTIONS[3]); // go to screen 2
                        this.imageEventText.updateDialogOption(0,OPTIONS[4]);
                        this.imageEventText.clearRemainingOptions();
                        screenNum = 2;
                        break;
                    case 3:
                        openMap();
                        break;
                }
                break;
            case 2:
                // screen 2
                switch(i){
                    case 0:
                        openMap();
                        break;
                }
        }
    }

}
