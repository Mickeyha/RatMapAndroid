// 貼到 Google Apps Script 編輯器，部署為 Web App
// 讓 Android App 可以抓到 Google Sheets 的老鼠資料

function doGet(e) {
  const sheet = SpreadsheetApp.getActiveSpreadsheet().getActiveSheet();
  const rows = sheet.getDataRange().getValues();
  const headers = rows[0];
  const data = [];

  for (let i = 1; i < rows.length; i++) {
    const row = rows[i];
    const obj = {};
    headers.forEach((header, index) => {
      obj[header] = row[index];
    });
    data.push(obj);
  }

  return ContentService
    .createTextOutput(JSON.stringify(data))
    .setMimeType(ContentService.MimeType.JSON);
}
