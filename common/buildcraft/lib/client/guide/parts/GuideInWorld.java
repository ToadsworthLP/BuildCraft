package buildcraft.lib.client.guide.parts;

import buildcraft.lib.client.guide.GuiGuide;
import buildcraft.lib.client.guide.world.WorldState;

public class GuideInWorld extends GuidePart {
    private boolean fullscreen = false;
    private final WorldState state;

    public GuideInWorld(GuiGuide gui, boolean fullscreen, WorldState state) {
        super(gui);
        this.fullscreen = fullscreen;
        this.state = state;
    }

    @Override
    public PagePart renderIntoArea(int x, int y, int width, int height, PagePart current, int index) {
        // TODO Auto-generated method stub
        throw new AbstractMethodError("Implement this!");
    }
    
    @Override
    public PagePart handleMouseClick(int x, int y, int width, int height, PagePart current, int index, int mouseX, int mouseY) {
        // TODO Auto-generated method stub
        throw new AbstractMethodError("Implement this!");
    }
}
