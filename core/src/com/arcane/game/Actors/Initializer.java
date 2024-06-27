package com.arcane.game.Actors;

import com.arcane.game.Actors.Cards.HandCards;
import com.arcane.game.Actors.Characters.Charlotte;
import com.arcane.game.Actors.Characters.Dracula.Dracula;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import java.util.LinkedList;

public class Initializer {
    private static final BitmapFont defaultFont = new BitmapFont(Gdx.files.internal("Font/default.fnt"));;

    public static class CharloSys extends Group{
        private final Charlotte charlotte;
        private final ProgressBar HPBar;
        private final Label HPLabel;

        public CharloSys(Charlotte charlotte, ProgressBar hpBar, Label HPLabel) {
            super();
            this.charlotte = charlotte;
            HPBar = hpBar;
            this.HPLabel = HPLabel;
            addActor(charlotte);
            //addActor(HPLabel);
            addActor(hpBar);
            addActor(HPLabel);
        }

        public Label getHPLabel() {
            return HPLabel;
        }
        public Charlotte getCharlotte() {
            return charlotte;
        }

        public ProgressBar getHPBar() {
            return HPBar;
        }
    }

    public static class DracuSys extends Group {
        private final Dracula dracula;
        private final ProgressBar HPBar;
        private final Label HPLabel;

        public DracuSys(Dracula dracula, ProgressBar hpBar, Label HPLabel) {
            super();
            this.dracula = dracula;
            HPBar = hpBar;
            this.HPLabel = HPLabel;
            addActor(dracula);
            //addActor(HPLabel);
            addActor(hpBar);
            addActor(HPLabel);
            this.setSize(dracula.getWidth(), dracula.getHeight());
        }

        public Label getHPLabel() {
            return HPLabel;
        }
        public Dracula getDracula() {
            return dracula;
        }

        public ProgressBar getHPBar() {
            return HPBar;
        }
    }
    public static CharloSys initializeCharlotte(LinkedList<Texture> textures,
                                                String path, float WORLD_WIDTH, float WORLD_HEIGHT, HandCards handCards) {
        Charlotte charlotte = new Charlotte(textures, path, WORLD_WIDTH, WORLD_HEIGHT, handCards);
        ProgressBar HPBar = initializeHPBar(charlotte.getMaxHP());
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = Initializer.defaultFont;
        style.fontColor = new Color(1, 1F, 0F, 1F);
        Label HPLabel = new Label(charlotte.getMaxHP() + " / " + charlotte.getMaxHP(), style);
        charlotte.linkHPLabel(HPLabel);
        charlotte.linkHPBar(HPBar);
        return new CharloSys(charlotte, HPBar, HPLabel);
    }

    public static DracuSys initializeDracuSys(Dracula dracula) {

        ProgressBar HPBar = initializeHPBar(dracula.getMaxHP());
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = Initializer.defaultFont;
        style.fontColor = new Color(1, 1F, 0F, 1F);
        Label HPLabel = new Label(dracula.getCurHP() + " / " + dracula.getMaxHP(), style);
        dracula.linkHPLabel(HPLabel);
        dracula.linkHPBar(HPBar);
        return dracula.linkSys(new DracuSys(dracula, HPBar, HPLabel));
    }



    public static ProgressBar initializeHPBar(int HP) {
        Skin skin = new Skin();
        // 创建一个纯白色的Pixmap
        Pixmap pixmap = new Pixmap(100, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        NinePatch ninePatchBackground = new NinePatch(new Texture(pixmap), 0, 0, 0, 0);
        Drawable backgroundDrawable = new NinePatchDrawable(ninePatchBackground);

        // 创建 ProgressBar 的样式
        ProgressBar.ProgressBarStyle progressBarStyle = new ProgressBar.ProgressBarStyle();
        progressBarStyle.background = backgroundDrawable;
        progressBarStyle.knob = null;
        progressBarStyle.knobBefore = ((NinePatchDrawable) backgroundDrawable).tint(Color.FIREBRICK); // 填充部分

        ProgressBar healthBar = new ProgressBar(0, HP, 1, false, progressBarStyle);
        healthBar.setValue(HP);
        return healthBar;
    }
}
