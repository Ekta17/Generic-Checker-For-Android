package ThreadAnnotations;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

import GenericDefualtPackage.GenericEffect;

//Android annotations to create a lattice
import android.support.annotation.MainThread;
import android.support.annotation.UiThread;

public class MainEffect implements GenericEffect {
    @Override
    public boolean LE(Class<? extends Annotation> left, Class<? extends Annotation> right) {
        assert (left != null && right != null);

        Class<? extends Annotation> leftEffect;
        Class<? extends Annotation> rightEffect;

        boolean leftBottom = (left.equals(MainThread.class)) ? true : false;

        if (leftBottom)
            leftEffect = MainThread.class;
        else
            leftEffect = UiThread.class;

        boolean rightTop = (right.equals(UiThread.class)) ? true : false;

        if (rightTop)
            rightEffect = UiThread.class;
        else
            rightEffect = MainThread.class;

        return leftBottom || rightTop || leftEffect.equals(rightEffect);

    }

    @Override
    public Class<? extends Annotation> min(Class<? extends Annotation> l, Class<? extends Annotation> r) {
        if (LE(l, r)) {
            return l;
        } else {
            return r;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MainEffect) {
            return this.equals((MainEffect) o);
        } else {
            return super.equals(o);
        }
    }
    
    @Override
    public ArrayList<Class<? extends Annotation>> getValidEffects() {
        ArrayList<Class<? extends Annotation>> listOfEffects=new ArrayList<Class<? extends Annotation>>();
        listOfEffects.add(MainThread.class);
        listOfEffects.add(UiThread.class);

        return listOfEffects;
    }

    @Override
    public Class<? extends Annotation> getTopMostEffectInLattice() {
        return UiThread.class;
    }

    @Override
    public Class<? extends Annotation> getBottomMostEffectInLattice() {
        return MainThread.class;
    }

}
