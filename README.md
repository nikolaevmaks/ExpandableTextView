# ExpandableTextView

Expandable text view with Yet text feature (when it is collapsed).
Text have 5 line count in collapsed state (customizable).

###How to use

```java
ExpandableTextView text;

text.setActualText("...Very big text...");
text.setYetText("Yet...");

text.setListener(new ExpandableTextView.Listener() {
@Override
	public void updateAppearance(ExpandableTextView expandableTextView, boolean isExpandAllowed) {
		expandableTextView.setBackgroundResource(isExpandAllowed ? R.drawable.selector : 0);
		expandableTextView.setOnClickListener(isExpandAllowed ? this : null);
	}
});
```




