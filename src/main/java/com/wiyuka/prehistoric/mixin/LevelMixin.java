package com.wiyuka.prehistoric.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Mixin(Level.class)
public abstract class LevelMixin {

    @Shadow protected abstract LevelEntityGetter<Entity> getEntities();

    /**
     * @author wiyuka
     * @reason why i need to write this things?
     */

    @Overwrite
    public List<Entity> getEntities(@Nullable Entity except, AABB box, Predicate<? super Entity> predicate) {
        Iterable<Entity> allEntities = this.getEntities().getAll();
        List<Entity> result = new ArrayList<>();

        for (Entity e : allEntities) {
            if (e != except && e.getBoundingBox().intersects(box)) {
                result.add(e);
            }
        }
        return result;
    }
}
