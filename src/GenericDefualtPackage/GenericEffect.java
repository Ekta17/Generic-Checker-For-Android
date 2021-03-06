package GenericDefualtPackage;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public interface GenericEffect {
	// Method to check Less than equal to Effect
	boolean LE(Class<? extends Annotation> left, Class<? extends Annotation> right);

	// Method to get minimum of (l, r)
	Class<? extends Annotation> min(Class<? extends Annotation> l, Class<? extends Annotation> r);

	class EffectRange {
		Class<? extends Annotation> min, max;

		public EffectRange(Class<? extends Annotation> min, Class<? extends Annotation> max) {
			assert (min != null || max != null);
			// If one is null, fill in with the other
			this.min = (min != null ? min : max);
			this.max = (max != null ? max : min);
		}
	}

	// Get the collection of valid effects. 
	ArrayList<Class<? extends Annotation>> getValidEffects();

	// Get the Top Most Effect of Lattice
	public Class<? extends Annotation> getTopMostEffectInLattice();

	// Get the Bottom Most Effect of Lattice
	public Class<? extends Annotation> getBottomMostEffectInLattice();
}
