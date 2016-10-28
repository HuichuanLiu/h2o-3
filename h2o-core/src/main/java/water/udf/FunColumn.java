package water.udf;

/**
 * This column depends on another column
 */
public class FunColumn<X, Y> implements Column<Y> {
  private final Function<X, Y> f;
  private final Column<X> xs;
  
  public FunColumn(Function<X, Y> f, Column<X> xs) {
    this.f = f;
    this.xs = xs;
  }
  
  @Override public Y get(long idx) { return isNA(idx) ? null : f.apply(xs.get(idx)); }

  @Override public boolean isNA(long idx) { return xs.isNA(idx); }

  @Override
  public String getString(long idx) { return isNA(idx) ? "(N/A)" : String.valueOf(get(idx)); }
}
