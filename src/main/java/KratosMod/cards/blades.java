package KratosMod.cards;

import KratosMod.characters.Kratos;
import KratosMod.patches.SatanTags;
import KratosMod.powers.Vengeance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import KratosMod.DefaultMod;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.Iterator;

import static KratosMod.DefaultMod.makeCardPath;

// public class blades extends AbstractDynamicCard
public class blades extends AbstractDynamicCard {


    /*
    RARE ATTACK
        ( 4 - 1 ) Blades of Chaos: Deal 25 (+15) damage.
        Apply 3 Weak. Gain 3 Vengeance. Exhaust.
     */


    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(blades.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("blades.png");// "public static final String IMG = makeCardPath("blades.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = 4;
    private static final int UPGRADED_COST = 3;

    private static final int DAMAGE = 25;
    private static final int UPGRADE_PLUS_DMG = 15;

    // /STAT DECLARATION/


    public blades() { // public blades() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        damage = baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 3;
        exhaust = true;
        this.tags.add(SatanTags.BLADES);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Vengeance(p, p, magicNumber), magicNumber));

        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var3.hasNext()) {
            AbstractMonster mo = (AbstractMonster)var3.next();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            // AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
