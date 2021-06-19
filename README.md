<!-- ⚠️ This README has been generated from the file(s) "blueprint.md" link - https://github.com/andreasbm/readme ⚠️--><p align="center">
  <img src="https://github.com/Gkemon/Easy-Android-Loading/blob/master/easy-loading.webp" alt="Logo" width="300" height="240"  />
</p>
<h1 align="center">Easy Loading For Android By Just A One Lined Code !!!</h1>
 <p align="center">
		<a href="https://github.com/Gkemon/Easy-Android-Loading/"><img alt="Maintained" src="https://img.shields.io/badge/Maintained%3F-yes-green.svg" height="20"/></a>
	<a href="https://github.com/Gkemon/Easy-Android-Loading/"><img alt="Maintained" src="https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg" height="20"/></a>

</p>

<p align="center">
  <b>Just configure a singleton loading instance once without additional boilerplate code and reuse it to show and hide from anywhere (Both Activity or Fragment) you want .</b></br>
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
  implementation 'com.github.Gkemon:Easy-Android-Loading:1.1'
}
```	
[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#getting-started-quick)



## ➤ Getting Started

### Setup default configuration :


```
class MainActivity : AppCompatActivity() {
     .
     .
     .
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
	
       /* It is the mandatory configuration which is needed to be
        * declared after calling setContentView --*/
        
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

### Setup custom configuration :


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
		  *background color is needed for the inputted layout then call it like that
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

          /* For showing loading just call  --> */
           LoadingPopup.showLoadingPopUp()
  
  
          /* For hiding loading just call --> */
           LoadingPopup.hideLoadingPopUp()
  
```
<p>
  <a href="https://www.linkedin.com/in/gk-mohammad-emon-0301b7104" rel="nofollow noreferrer">
    <img src="https://i.stack.imgur.com/gVE0j.png" alt="linkedin"> LinkedIn
  </a> &nbsp; 
  <a href="emon.info2013@gmail.com">
   <img width="20" src="https://user-images.githubusercontent.com/5141132/50740364-7ea80880-1217-11e9-8faf-2348e31beedd.png" alt="inbox"> Inbox
  </a>
</p>

#### Logo credit: [Alex Gorbunov](https://dribbble.com/shots/11116681-Spiral-pre-loader-concept)

## ➤ License

The source code is licensed under the [Apache License 2.0](https://github.com/Gkemon/XML-to-PDF-generator/blob/master/LICENSE). 


[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#license)


