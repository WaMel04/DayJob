package com.wamel.dayjob.addon;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import com.wamel.dayjob.DayJob;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class Expr$Level extends SimpleExpression<Double> {
    private Expression<String> uuid;

    public boolean init(Expression[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.uuid = expressions[0];
        return true;
    }

    public String toString(@Nullable Event event, boolean b) {
        return "(%string%'s job level|job level of %string%)";
    }

    @Nullable
    protected Double[] get(Event event) {
        String uuid = (String)this.uuid.getSingle(event);
        return new Double[] {Double.valueOf(DayJob.getLevel(uuid))};
    }

    public boolean isSingle() {
        return true;
    }

    public Class<? extends Double> getReturnType() {
        return Double.class;
    }

    public void change(Event event, Object[] d, Changer.ChangeMode mode) {
        String uuid = (String)this.uuid.getSingle(event);
        double num = Double.parseDouble(String.valueOf(d[0]));
        if (mode == Changer.ChangeMode.ADD) {
            DayJob.addLevel(uuid, (int) num);
        } else if (mode == Changer.ChangeMode.REMOVE) {
            DayJob.addLevel(uuid, (int) -num);
        } else if (mode == Changer.ChangeMode.SET) {
            DayJob.setLevel(uuid, (int) num);
        }
    }

    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET)
            return (Class[])CollectionUtils.array((Object[])new Class[] { Number.class });
        if (mode == Changer.ChangeMode.ADD)
            return (Class[])CollectionUtils.array((Object[])new Class[] { Number.class });
        if (mode == Changer.ChangeMode.REMOVE)
            return (Class[])CollectionUtils.array((Object[])new Class[] { Number.class });
        return null;
    }
}
