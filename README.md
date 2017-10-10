# Android Alertify

## Introduction

> Alertify is a snackbar library that makes your live easier with some awesome options.

## Code Samples

**Simple Alertify**
```java
        ViewGroup view = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        Alertify.make(view, Alertify.LAYOUT_NORMAL, Alertify.LENGTH_SHORT)
                .setText("This is a Alertify")
                .show();
```

**Button Alertify**
```java
        ViewGroup view = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        Alertify.make(view, Alertify.LAYOUT_WITH_BUTTON, Alertify.LENGTH_SHORT)
                .setText("This is a Alertify")
                .setButtonAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Button has been clicked!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
```

**Icon Alertify**
```java
        ViewGroup view = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        Alertify.make(view, Alertify.LAYOUT_WITH_ICON, Alertify.LENGTH_SHORT)
                .setText("This is a Alertify")
                .setIcon(R.drawable.notification_succeed)
                .setIconAnimation(Alertify.ANIMATION_PULSE)
                .show();
```

**Custom layout Alertify**
```java
        ViewGroup view = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        Alertify alertify = Alertify.make(view, Alertify.LAYOUT_ALERTIFY_CUSTOM_EXAMPLE, Alertify.LENGTH_SHORT);
        final TextView tvCustom = (TextView) alertify.getView().findViewById(R.id.tvCustom);
        Button btnCustom = (Button) alertify.getView().findViewById(R.id.btnCustom);
        
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCustom.setText("This is a custom Alertify");
            }
        });
        alertify.show();
```

**Position Alertify**
```java
        ViewGroup view = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        Alertify.make(view, Alertify.LAYOUT_NORMAL, Alertify.LENGTH_SHORT)
                .setText("This is a Alertify")
                .setPosition(Alertify.POSITION_TOP)
                //.setPosition(Alertify.POSITION_CENTER)
                //.setPosition(Alertify.POSITION_BOTTOM)
                .show();
```

**Types Alertify**
```java
        ViewGroup view = (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        Alertify.make(view, Alertify.LAYOUT_ALERTIFY_DEFAULT, Alertify.LENGTH_SHORT)
        //Alertify.make(view, Alertify.LAYOUT_ALERTIFY_SUCCEED, Alertify.LENGTH_SHORT)
        //Alertify.make(view, Alertify.LAYOUT_ALERTIFY_ERROR, Alertify.LENGTH_SHORT)
                .setTitle("This is a Alertify title")
                .setSubTitle("This is a Alertify subtitle")
                .setPosition(Alertify.POSITION_TOP)
                .setIconAnimation(Alertify.ANIMATION_PULSE)
               .show();
```

## Mention
This is my first library. The library contains some bugs.
Feel free to try it out and please if you have some feedback, i'll like it.

