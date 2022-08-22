package com.wamel.dayjob.addon;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.wamel.dayjob.DayJob;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class Expr$MaxExp extends SimpleExpression<Double> {
    private Expression<String> uuid;

    public boolean init(Expression[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.uuid = expressions[0];
        return true;
    }

    public String toString(@Nullable Event event, boolean b) {
        return "(%string%'s job maxexp|job maxexp of %string%)";
    }

    @Nullable
    protected Double[] get(Event event) {
        String uuid = (String)this.uuid.getSingle(event);
        return new Double[] {Double.valueOf(DayJob.getMaxExp(uuid))};
    }

    public boolean isSingle() {
        return true;
    }

    public Class<? extends Double> getReturnType() {
        return Double.class;
    }
}
