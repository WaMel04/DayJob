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

public class Expr$Job extends SimpleExpression<String> {
    private Expression<String> uuid;

    public boolean init(Expression[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.uuid = expressions[0];
        return true;
    }

    public String toString(@Nullable Event event, boolean b) {
        return "(%string%'s job|job of %string%)";
    }

    @Nullable
    protected String[] get(Event event) {
        String uuid = (String)this.uuid.getSingle(event);
        return new String[] {(String) DayJob.getJob(uuid)};
    }

    public boolean isSingle() {
        return true;
    }

    public Class<? extends String> getReturnType() {
        return String.class;
    }

    public void change(Event event, Object[] d, Changer.ChangeMode mode) {
        String uuid = (String)this.uuid.getSingle(event);
        String text = String.valueOf(d[0]);
        if (mode == Changer.ChangeMode.SET)
            DayJob.setJob(uuid, text);
    }

    public Class<?>[] acceptChange(Changer.ChangeMode mode) {
        if (mode == Changer.ChangeMode.SET)
            return (Class[])CollectionUtils.array((Object[])new Class[] { String.class });
        return null;
    }
}
