# 老鼠地圖 RatMap Android

**中文** | [English](#english)

台北市鼠患通報地圖 POC — 以 Google Maps + Marker Clustering 展示各地鼠患回報點位。  
目前為 **示範版本**，資料為 Mock 寫死，重點在於展示地圖互動功能。

> ⚠️ **注意**：`rat_data.json` 內的資料為示範用途，請自行替換為實際來源的 Live 資料。

---

## 功能

- Google Maps 顯示鼠患通報點位
- Marker Clustering — 自動聚合密集點位，縮放時展開
- 依嚴重程度顯示不同顏色 Marker（🟢 低 / 🟠 中 / 🔴 高）
- 點擊 Marker 顯示地點、行政區、日期、嚴重程度、描述

## 截圖

> _(待補)_

## 技術架構

| 項目 | 說明 |
|------|------|
| 語言 | Kotlin |
| 最低 SDK | Android 8.0 (API 26) |
| 目標 SDK | Android 15 (API 35) |
| 地圖 | Google Maps SDK for Android 19.x |
| Clustering | Maps Android Utils 3.x |
| 資料解析 | Gson |

---

## 快速開始 Quick Start

### 1. 申請 Google Maps API Key

1. 前往 [Google Cloud Console](https://console.cloud.google.com/)
2. 建立或選擇一個專案
3. 前往 **APIs & Services → Credentials**
4. 點選 **Create Credentials → API Key**，複製產生的 Key（格式：`AIza...`）

### 2. 啟用 Maps SDK for Android

1. 在 Google Cloud Console 上方搜尋列輸入 **「Maps SDK for Android」**
2. 點進去後按 **啟用（Enable）**
3. 確認 API 狀態為「已啟用」

> 建議在 Credentials 頁面為這個 Key 設定 **Android 應用程式限制**（填入 Package name 與 SHA-1），避免 Key 被濫用。

### 3. 設定 API Key（本機）

在專案根目錄的 `local.properties` 加入以下一行：

```properties
MAPS_API_KEY=你的_API_KEY
```

> `local.properties` 已在 `.gitignore`，不會被上傳至 GitHub。

### 4. Build & Run

```bash
# Clone 專案
git clone https://github.com/Mickeyha/RatMapAndroid.git

# 用 Android Studio 開啟，等待 Gradle Sync 完成後執行
```

---

## 替換資料

目前地圖資料位於：

```
app/src/main/res/raw/rat_data.json
```

格式如下，請依此結構替換為實際來源資料：

```json
[
  {
    "name": "地點名稱",
    "address": "完整地址",
    "district": "行政區",
    "lat": 25.0499,
    "lng": 121.5774,
    "date": "2026-04-01",
    "severity": 5,
    "description": "描述文字"
  }
]
```

**嚴重程度（severity）說明：**

| 值 | 顏色 | 說明 |
|----|------|------|
| 1–2 | 🟢 綠色 | 輕微 |
| 3 | 🟠 橘色 | 中等 |
| 4–5 | 🔴 紅色 | 嚴重 |

---

---

## English

# RatMap Android

A POC rat sighting map for Taipei, built with Google Maps SDK + Marker Clustering.  
This is a **demonstration version** — map data is hardcoded mock data focused on showcasing the map interaction features.

> ⚠️ **Note**: The data in `rat_data.json` is for demonstration purposes only. Please replace it with your own live data source.

---

## Features

- Displays rat sighting reports on Google Maps
- Marker Clustering — auto-groups dense markers, expands on zoom
- Color-coded markers by severity (🟢 Low / 🟠 Medium / 🔴 High)
- Tap a marker to view location, district, date, severity, and description

## Tech Stack

| Item | Detail |
|------|--------|
| Language | Kotlin |
| Min SDK | Android 8.0 (API 26) |
| Target SDK | Android 15 (API 35) |
| Maps | Google Maps SDK for Android 19.x |
| Clustering | Maps Android Utils 3.x |
| Parsing | Gson |

---

## Quick Start

### 1. Get a Google Maps API Key

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create or select a project
3. Navigate to **APIs & Services → Credentials**
4. Click **Create Credentials → API Key** and copy the generated key (format: `AIza...`)

### 2. Enable Maps SDK for Android

1. In the Google Cloud Console search bar, type **"Maps SDK for Android"**
2. Open the result and click **Enable**
3. Confirm the API status shows as "Enabled"

> It is recommended to add **Android app restrictions** to your key (Package name + SHA-1 fingerprint) to prevent unauthorized usage.

### 3. Configure Your API Key (Local)

Add the following line to `local.properties` in the project root:

```properties
MAPS_API_KEY=YOUR_API_KEY_HERE
```

> `local.properties` is included in `.gitignore` and will never be committed to GitHub.

### 4. Build & Run

```bash
# Clone the repository
git clone https://github.com/Mickeyha/RatMapAndroid.git

# Open in Android Studio, wait for Gradle Sync, then Run
```

---

## Replacing Mock Data

The map data is located at:

```
app/src/main/res/raw/rat_data.json
```

Replace it with your live data using the following schema:

```json
[
  {
    "name": "Location name",
    "address": "Full address",
    "district": "District",
    "lat": 25.0499,
    "lng": 121.5774,
    "date": "2026-04-01",
    "severity": 5,
    "description": "Description text"
  }
]
```

**Severity levels:**

| Value | Color | Meaning |
|-------|-------|---------|
| 1–2 | 🟢 Green | Minor |
| 3 | 🟠 Orange | Moderate |
| 4–5 | 🔴 Red | Severe |
