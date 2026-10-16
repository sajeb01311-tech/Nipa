# NipaAI APK বানানোর নির্দেশনা (শুধু মোবাইল দিয়ে)

## ১) GitHub-এ প্রজেক্ট আপলোড
1. GitHub-এ একটি নতুন repository তৈরি করো, যেমন `NipaAI`।
2. এই ZIP extract করো। ZIP-এর ভেতরের `NipaAI` ফোল্ডারের **ভেতরের সব ফাইল ও ফোল্ডার** repository-র root-এ upload করতে হবে। অর্থাৎ `settings.gradle.kts`, `build.gradle.kts`, `app/`, এবং `.github/` সরাসরি repository-র root-এ থাকবে।
3. `.github/workflows/build-android.yml` ফাইলটিও অবশ্যই upload হবে। GitHub ওয়েবসাইটে hidden `.github` ফোল্ডার আপলোড করা কঠিন হলে GitHub-এর web editor দিয়ে ফাইলটি তৈরি করো; path হবে `.github/workflows/build-android.yml`।

## ২) Build চালানো
1. Repository-র `Actions` tab খোলো।
2. বাম পাশ থেকে `Build NipaAI APK` নির্বাচন করো।
3. `Run workflow` চাপো। প্রথম push-এ workflow নিজেও চালু হতে পারে।
4. Build শেষ হলে সবুজ checkmark দেখাবে। লাল X হলে build log খুলে error দেখতে হবে।

## ৩) APK নামানো
1. সফল workflow run খুলে নিচের `Artifacts` অংশে যাও।
2. `NipaAI-debug-apk` চাপ দিয়ে ZIP download করো।
3. ZIP extract করে `app-debug.apk` বের করো।
4. APK ফোনে install করো। Android চাইলে “Install unknown apps” অনুমতি দিতে হবে। শুধু নিজের তৈরি/বিশ্বস্ত APK-ই install করবে।

## গুরুত্বপূর্ণ
- এই workflow শুধু APK build ও unit test চালানোর ব্যবস্থা যোগ করে; আমি এখানে build চালাইনি এবং APK সফল হবে—এমন নিশ্চয়তা দিচ্ছি না। GitHub Actions-এর log-ই প্রকৃত ফল দেখাবে।
- এই Phase 2-তে AI backend, বাস্তব voice conversation, এবং প্রকৃত lip-sync/face animation সম্পূর্ণ আছে—এমন ধরে নিও না।
- `minSdk = 26`, তাই Android 8.0 বা তার পরের সংস্করণ দরকার; Android 10-এ install হওয়ার কথা।
