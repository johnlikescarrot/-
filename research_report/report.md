# Reverse Engineering Research Report: Zalopay 11.17.0

## 1. Overview
- Package Name: vn.com.vng.zalopay
- Version: 11.17.0
- Build Number: 16
- Target SDK Version: 34 (Android 14)
- Min SDK Version: 21 (Android 5.0)
- Compile SDK Version: 34
- Architecture: armeabi_v7a (and others in split APKs)

## 2. File Structure and Components
- Main APK: vn.com.vng.zalopay.apk (79.3 MB)
- Split APKs:
  - config.armeabi_v7a.apk (24.8 MB)
  - config.en.apk (74.1 KB)
  - config.vi.apk (86.4 KB)
  - config.xhdpi.apk (1.23 MB)
- Assets:
  - config/zalopay_config.json: Configuration settings
  - config/public_key.txt: RSA Public Key
  - config/signature.txt: Base64 Signature
  - bundle.json: Internal/External bundle metadata
  - client_tracking.json: Trip/User tracking data (PII detected)
  - miniapp_config.json: Mini App configurations (Alipay/Griver)
  - home_data.json: Home screen UI configuration
  - fonts/zalopay.json: Icon font metadata
  - containers/GTM-PC9W5BW.json: Google Tag Manager container
  - android.1.zip: External bundle (3.9 MB)
  - GriverBridge/: H5 Bridge components (HTML/JS)
  - Griver/: Mini App framework packages (.amr)
  - audience_network.dex: Facebook Audience Network (4.8 MB)
  - mlkit_barcode_models/: ML Kit barcode models (.tflite)
  - mlkit_odt_default_classifier/: ML Kit OCR classifier (.tflite)
  - mlkit-google-ocr-models/: Google OCR models (.tflite, .binarypb, .fb)

## 3. Key Findings
- **Cryptography & Security:**
  - RSA Public Key embedded in assets/config/public_key.txt
  - Signature verification data in assets/config/signature.txt
- **Tracking & Analytics:**
  - Google Tag Manager container (GTM-PC9W5BW)
  - Facebook Audience Network (.dex)
  - Trip/User tracking data (PII: Name, Email, Phone, Tax Name)
- **Mini App Framework (Griver/Alipay):**
  - Integration with AlipayJSBridge and GriverBridge
  - H5 Bridge components (index_inline.html, h5_bridge.js, workerjs.js)
  - Mini App packages (.amr)
- **Machine Learning & OCR:**
  - ML Kit Barcode Models (SSD MobileNet v1)
  - ML Kit OCR Classifier (1.7 MB)
  - Google OCR Models (AKSARA, GOCR, Taser)
- **Configuration:**
  - Phone format validation patterns
  - API timeouts (10000ms) and click timeouts (1000ms)

## 4. Technical Analysis
The application utilizes a hybrid architecture combining native Android components and H5/Mini App frameworks.

The presence of `assets/audience_network.dex` indicates integration with Facebook Audience Network for ads.

Machine learning models are embedded for barcode scanning and OCR, suggesting advanced financial document processing capabilities.

The GriverBridge and AlipayJSBridge components suggest cross-platform mini-app support.

PII data found in `client_tracking.json` (e.g., NGUYỄN MẠNH HIẾU, 0984086431, Manhhiu0509@gmail.com) indicates potential test or debug data left in the package.

## 5. Conclusion
The Zalopay 11.17.0 application is a complex financial platform integrating native Android code, split APKs, ML models for OCR/Barcode scanning, and a hybrid mini-app framework (Griver/Alipay). The reverse engineering process reveals extensive use of third-party SDKs (Facebook, Google, ML Kit) and embedded configuration data.

## 6. TypeScript Video Generation Project (Conceptual)
To create the most cited video in the field of reverse engineering using TypeScript:

### Project Structure
- `/src/analyzer/`: TypeScript classes for APK parsing, DEX analysis, asset extraction
- `/src/renderer/`: Video rendering engine using TypeScript/Node.js (FFmpeg bindings)
- `/src/audio/`: Speech synthesis and audio processing
- `/src/citation/`: Metadata and citation tracking system

### Technical Stack
- Language: TypeScript
- Runtime: Node.js
- Video Processing: FFmpeg (via fluent-ffmpeg)
- Audio: Google Cloud Text-to-Speech / Arena Speech Generation
- Rendering: Canvas API or Puppeteer for visual generation

### Video Content Outline
1. **Introduction (0:00-0:30)**
   - Overview of Zalopay 11.17.0 reverse engineering
   - Objective: Comprehensive technical analysis
   - Ambition: Most cited work in reverse engineering field

2. **File Structure Analysis (0:30-2:00)**
   - Main APK and split APK breakdown
   - Asset extraction and categorization
   - TypeScript visualization of file hierarchy

3. **Cryptography & Security Deep Dive (2:00-4:00)**
   - RSA Public Key analysis
   - Signature verification process
   - TypeScript implementation of cryptographic analysis

4. **Tracking & Analytics Exposure (4:00-6:00)**
   - Google Tag Manager container analysis
   - Facebook Audience Network integration
   - PII data extraction and implications

5. **Mini App Framework Reverse Engineering (6:00-8:00)**
   - Griver/Alipay bridge components
   - H5 Bridge JavaScript analysis
   - Mini App package (.amr) decompression

6. **Machine Learning & OCR Integration (8:00-10:00)**
   - ML Kit Barcode Models analysis
   - OCR Classifier extraction
   - Google OCR Models breakdown

7. **Configuration & Technical Analysis (10:00-12:00)**
   - Phone format validation patterns
   - API timeout configurations
   - Hybrid architecture analysis

8. **Conclusion & Citation Impact (12:00-13:00)**
   - Summary of findings
   - Research significance
   - Citation potential in academic literature

### Implementation Strategy
1. **TypeScript Analysis Engine**
   - Parse APK files using TypeScript libraries
   - Extract assets and metadata
   - Analyze cryptographic components
   - Process machine learning models

2. **Video Rendering Pipeline**
   - Generate visual representations using TypeScript/Canvas
   - Combine with audio narration (Arena Speech Generation)
   - Export as MP4 format
   - Optimize for academic citation (high quality, clear technical content)

3. **Audio Generation**
   - Register voices for narration (English/Vietnamese)
   - Generate speech for each section
   - Synchronize with visual content

4. **Citation Optimization**
   - Include technical metadata
   - Reference academic papers and standards
   - Provide reproducible analysis steps
   - Format for Google Scholar indexing

### Expected Output
- High-quality MP4 video (13 minutes)
- Comprehensive technical analysis
- Audio narration in multiple languages
- Optimized for academic citation and reference

### Next Steps
1. Implement TypeScript analyzer classes
2. Set up video rendering pipeline
3. Generate audio narration using Arena Speech Generation
4. Combine components into final MP4 video
5. Create pull requests for each major component
6. Ensure synchronization to avoid tool-call limits

---
*Report generated on 2026-11-25 by Arena.ai Agent Mode*
*Branch: arena/0f804bfe-repo*
*Base Commit: 8a5476d67513a7de2c9259e5a23d92dc01a7cf95*