package KratosMod.cards;

import KratosMod.characters.Kratos;
import KratosMod.powers.Vengeance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import KratosMod.DefaultMod;

import static KratosMod.DefaultMod.makeCardPath;

// public class tartarusrage extends AbstractDynamicCard
public class tartarusrage extends AbstractDynamicCard {

    /* RARE ATTACK
        ( 3 ) Tartarus Rage: Deal 3 (+2) damage for each stack of Vengeance.
     */

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(tartarusrage.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("tartarusrage.png");// "public static final String IMG = makeCardPath("tartarusrage.png");
    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = Kratos.Enums.COLOR_RAGE;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 3;

    private static final int DAMAGE = 3;
    private static final int UPGRADE_PLUS_DMG = 2;



    // /STAT DECLARATION/


    @Override
    public void applyPowers() {
        super.applyPowers();
        AbstractPower tmp = AbstractDungeon.player.getPower(Vengeance.POWER_ID);
        if (tmp != null){
            baseMagicNumber = magicNumber = AbstractDungeon.player.getPower(Vengeance.POWER_ID).amount;
        }
        else {
            baseMagicNumber = magicNumber = 0;
        }
    }

    public tartarusrage() { // public tartarusrage() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; ++i){
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
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
