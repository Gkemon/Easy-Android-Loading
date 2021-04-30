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
                            .setBackgroundOpacity(opacity)/*Int between 0-100*/
                            .build()
        .
	.	    
       }
```


### How to print an Invoice Or Report ? 
Sometimes people gets stuck to print invoice or report via this library.So I wrote an example invoice/report printing fragment to visualise how to print an <b>Invoice</b> or <b>Report</b>. Here is [the link also with an important documentation](https://github.com/Gkemon/Android-XML-to-PDF-Generator/blob/608b873e9f21ed1dbf345a191337a5a548fd3517/sample/src/main/java/com/emon/exampleXMLtoPDF/demoInvoice/DemoInvoiceFragment.java#L61) 


### How to deal with generated PDF? 
With a method calling named `openPDFafterGeneration(true)`, the generated file will be automatically opened automatically.So you <b>DON'T NEED TO BE BOTHER FOR IT</b>. [FileProvider](https://developer.android.com/reference/androidx/core/content/FileProvider) is used to open file here. To get a good insight about it please see the [tutorial](https://vladsonkin.com/how-to-share-files-with-android-fileprovider/). The `android:authorities` name in the app is `${applicationId}.xmlToPdf.provider` which might be needed if you want to deal with generated file <b>CUSTOMLY</b>,not letting the app open the generated file. you will get the generated file path in `onSuccess(SuccessResponse response)` response.

### Troubleshoot
* For WRAP_CONTENT page size, try to avoid to provide `match_parent` and `wrap_content` height/width in XML. So it specifically. 
* If any of your footer view is not placed the footer position then you need adjust it using `marginTop` and keep it in a `ScrollView`.For example this [issue](https://github.com/Gkemon/Android-XML-to-PDF-Generator/issues/16) is fixed by rearranging XML like [this](https://github.com/Gkemon/Android-XML-to-PDF-Generator/blob/master/sample/src/main/res/layout/layout_test_invoice.xml)

So if you find any trouble,then you are also welcomed again to knock me.Thank you so much. 
		

[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#templates)

<p>
  <a href="https://www.linkedin.com/in/gk-mohammad-emon-0301b7104" rel="nofollow noreferrer">
    <img src="https://i.stack.imgur.com/gVE0j.png" alt="linkedin"> LinkedIn
  </a> &nbsp; 
  <a href="emon.info2013@gmail.com">
   <img width="20" src="https://user-images.githubusercontent.com/5141132/50740364-7ea80880-1217-11e9-8faf-2348e31beedd.png" alt="inbox"> Inbox
  </a>
</p>

#### Logo credit: [kirillmazin](https://www.behance.net/kirillmazin)

## ➤ License

The source code is licensed under the [Apache License 2.0](https://github.com/Gkemon/XML-to-PDF-generator/blob/master/LICENSE). 


[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#license)


