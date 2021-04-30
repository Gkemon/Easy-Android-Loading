<!-- ⚠️ This README has been generated from the file(s) "blueprint.md" link - https://github.com/andreasbm/readme ⚠️--><p align="center">
  <img src="https://github.com/Gkemon/Easy-Android-Loading/blob/master/easy-loading.webp" alt="Logo" width="300" height="240"  />
</p>
<h1 align="center">Easy Loading For Android By Just One Lined Code !!!</h1>
 <p align="center">
		<a href="https://github.com/Gkemon/Easy-Android-Loading/"><img alt="Maintained" src="https://img.shields.io/badge/Maintained%3F-yes-green.svg" height="20"/></a>
	<a href="https://github.com/Gkemon/Easy-Android-Loading/"><img alt="Maintained" src="https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg" height="20"/></a>

</p>

<p align="center">
  <b>Just configure a singleton loading instance once and show and hide from anywhere (Activity or Fragment).</b></br>
</p>

<br />


<details>
<summary>📖 Table of Contents</summary>
<br />

[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#table-of-contents)

## ➤ Table of Contents

* [➤ Installation](#-installation)
* [➤ Getting Started](#-getting-started)
* [➤ License](#-license)
</details>


[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#installation)

## ➤ Installation

**Step 1**. Add the JitPack repository to your root ```build.gradle``` at the end of repositories
```
android {
 .
 .
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
.
.
```

**Step 2**. Add the dependency
```
dependencies {
  implementation 'com.github.Gkemon:Easy-Android-Loading:v1.0'
}
```	
[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#getting-started-quick)



## ➤ Getting Started

### Setup default configure :


```
class MainActivity : AppCompatActivity() {
     .
     .
     .
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
	
       /* It is the Mandatory configuration which is needed to be
        * declare after calling setContentView --*/
        
       LoadingPopup.getInstance(activity)
                   .defaultLovelyLoading()
                   .build()
		   
	/*OR*/	   
		   
       /* If adding background color or opacity is needed then -- */
        LoadingPopup.getInstance(activity)
                            .defaultLovelyLoading()
                            .setBackgroundColor(android.R.color.holo_red_dark)
                            .setBackgroundOpacity(myBackgroundOpacity)/*Int between 0-100*/
                            .build()
        .
	.	    
       }
```

### Setup custom configure :


```
class MainActivity : AppCompatActivity() {
     .
     .
     .
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
	
       LoadingPopup.getInstance(activity)
                .customLoading()
                .setCustomViewID(R.layout.layout_my_custom_loading)
		 /*layout resource id which holds the custom loading view. If setting up  
		  *background color is needed for inputted layout then call it like that
		  *setCustomViewID(R.layout.layout_my_custom_loading,R.color.my_color)*/
                .doIntentionalDelay()
		/*If intentional delay is needed. Otherwise call .noIntentionalDelay()*/
                .setDelayDurationInMillSec(5000)
                .setBackgroundOpacity(myBackgroundOpacity)
                .build()

        .
	.	    
       }
```

### Showing or Hiding Loading:

```

  /* For showing loading just call--*/
  LoadingPopup.showLoadingPopUp()
  
  /* For hiding loading just call--*/
  LoadingPopup.hideLoadingPopUp()
  
```


#### Logo credit: [Alex Gorbunov](https://dribbble.com/shots/11116681-Spiral-pre-loader-concept)

## ➤ License

The source code is licensed under the [Apache License 2.0](https://github.com/Gkemon/XML-to-PDF-generator/blob/master/LICENSE). 


[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#license)


