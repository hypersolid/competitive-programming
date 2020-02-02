abstract static class Tuple<T extends Comparable<T>> implements Comparable<T> {
    @Override
    public int hashCode() {
        return Objects.hash(values());
    }

    private Object[] values() {
        Object[] result = new Object[fields().length];
        System.arraycopy(fields(), 0, result, 0, fields().length);
        return result;
    }

    @Override
    public String toString() {
        return Arrays.stream(fields())
                .map(
                        (f) -> {
                            try {
                                return f.get(this).toString();
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        })
                .collect(Collectors.joining(", ", "(", ")"));
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public int compareTo(T other) {
        int result = 0;
        for (Field field : fields()) {
            try {
                result = ((T) field.get(this)).compareTo((T) field.get(other));
                if (result != 0) return result;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    private Field[] fields() {
        Field[] fields;
        try {
            fields = (Field[]) this.getClass().getDeclaredField("fields").get(this.getClass());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        if (fields != null) return fields;

        fields = this.getClass().getFields();
        Arrays.sort(fields, Comparator.comparing(Field::getName));
        return fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        for (Field field : fields()) {
            try {
                if (!Objects.equals(field.get(this), field.get(o))) return false;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}

static class Pair<T extends Comparable<T>> extends Tuple {
    protected static Field[] fields;

    public final T v1;
    public final T v2;

    public Pair(T v1, T v2) {
        this.v1 = v1;
        this.v2 = v2;
    }
}

static class Triple<T extends Comparable<T>> extends Pair<T> {
    protected static Field[] fields;

    public final T v3;

    public Triple(T v1, T v2, T v3) {
        super(v1, v2);
        this.v3 = v3;
    }
}
