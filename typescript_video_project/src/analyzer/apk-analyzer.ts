// src/analyzer/apk-analyzer.ts
import * as fs from 'fs';
import * as path from 'path';
import * as zip from 'adm-zip';

export interface AnalysisResult {
  totalFiles: number;
  assets: string[];
  dexFiles: string[];
  xmlFiles: string[];
  cryptographicComponents: CryptographicData;
  mlModels: MLModelData[];
  trackingData: TrackingData;
  miniAppFramework: MiniAppData;
  configuration: ConfigurationData;
}

export interface CryptographicData {
  publicKey: string;
  signature: string;
  verificationMethods: string[];
}

export interface MLModelData {
  modelName: string;
  path: string;
  size: number;
  type: 'barcode' | 'ocr' | 'classifier' | 'layout';
}

export interface TrackingData {
  googleTagManagerContainer: string;
  facebookAudienceNetwork: boolean;
  piiData: PIIData;
}

export interface PIIData {
  name: string;
  email: string;
  phone: string;
  taxName: string;
  customerId: string;
  sessionId: string;
}

export interface MiniAppData {
  griverBridge: GriverBridgeData;
  alipayJSBridge: boolean;
  amrPackages: string[];
  config: MiniAppConfig;
}

export interface GriverBridgeData {
  htmlFiles: string[];
  jsFiles: string[];
  workerFiles: string[];
}

export interface MiniAppConfig {
  appId: string;
  appName: string;
  baseUrl: string;
  deepLink: string;
  category: string;
}

export interface ConfigurationData {
  phoneFormatPatterns: string[];
  apiTimeout: number;
  clickTimeout: number;
  version: string;
  buildNumber: number;
}

export class APKAnalyzer {
  private apkPath: string;
  private zip: zip;

  constructor(apkPath: string) {
    this.apkPath = apkPath;
    this.zip = new zip(apkPath);
  }

  analyzeStructure(): AnalysisResult {
    const files = this.zip.getEntries();
    
    // Extract assets
    const assets = files
      .filter(f => f.entryName.startsWith('assets/'))
      .map(f => f.entryName);

    // Extract DEX files
    const dexFiles = files
      .filter(f => f.entryName.endsWith('.dex'))
      .map(f => f.entryName);

    // Extract XML files
    const xmlFiles = files
      .filter(f => f.entryName.endsWith('.xml'))
      .map(f => f.entryName);

    // Extract cryptographic components
    const publicKey = this.extractAsset('assets/config/public_key.txt').toString('utf8');
    const signature = this.extractAsset('assets/config/signature.txt').toString('utf8');

    // Extract ML models
    const mlModels = assets
      .filter(a => a.includes('mlkit') || a.includes('tflite') || a.includes('binarypb'))
      .map(a => ({
        modelName: path.basename(a),
        path: a,
        size: this.extractAsset(a).length,
        type: this.determineModelType(a)
      }));

    // Extract tracking data
    const trackingData = this.extractTrackingData();

    // Extract mini app framework data
    const miniAppData = this.extractMiniAppData();

    // Extract configuration
    const configuration = this.extractConfiguration();

    return {
      totalFiles: files.length,
      assets,
      dexFiles,
      xmlFiles,
      cryptographicComponents: {
        publicKey,
        signature,
        verificationMethods: ['RSA', 'SHA-256', 'Base64']
      },
      mlModels,
      trackingData,
      miniAppFramework,
      configuration
    };
  }

  private extractAsset(assetPath: string): Buffer {
    const entry = this.zip.getEntry(assetPath);
    return entry ? entry.getData() : Buffer.alloc(0);
  }

  private determineModelType(path: string): MLModelData['type'] {
    if (path.includes('barcode')) return 'barcode';
    if (path.includes('ocr') || path.includes('classifier')) return 'ocr';
    if (path.includes('layout')) return 'layout';
    return 'classifier';
  }

  private extractTrackingData(): TrackingData {
    // Extract from assets/client_tracking.json and GTM container
    const clientTracking = JSON.parse(
      this.extractAsset('assets/client_tracking.json').toString('utf8')
    );
    
    const gtmContainer = JSON.parse(
      this.extractAsset('assets/containers/GTM-PC9W5BW.json').toString('utf8')
    );

    return {
      googleTagManagerContainer: gtmContainer.fingerprint,
      facebookAudienceNetwork: this.zip.getEntry('assets/audience_network.dex') !== null,
      piiData: {
        name: clientTracking.tripDetail.customerName,
        email: clientTracking.tripDetail.customerEmail,
        phone: clientTracking.tripDetail.customerMobileNo,
        taxName: clientTracking.tripDetail.customerTaxName,
        customerId: clientTracking.tripDetail.customerId,
        sessionId: clientTracking.tripDetail.customerSessionId
      }
    };
  }

  private extractMiniAppData(): MiniAppData {
    // Extract GriverBridge components
    const griverBridgeFiles = this.zip.getEntries()
      .filter(f => f.entryName.startsWith('assets/GriverBridge/'))
      .map(f => f.entryName);

    const htmlFiles = griverBridgeFiles.filter(f => f.endsWith('.html'));
    const jsFiles = griverBridgeFiles.filter(f => f.endsWith('.js'));
    const workerFiles = griverBridgeFiles.filter(f => f.includes('worker'));

    // Extract AMR packages
    const amrPackages = this.zip.getEntries()
      .filter(f => f.entryName.endsWith('.amr'))
      .map(f => f.entryName);

    // Extract mini app config
    const config = JSON.parse(
      this.extractAsset('assets/config/miniapp_config.json').toString('utf8')
    ).data[0];

    return {
      griverBridge: { htmlFiles, jsFiles, workerFiles },
      alipayJSBridge: jsFiles.some(f => f.includes('AlipayJSBridge')),
      amrPackages,
      config: {
        appId: config.app_id,
        appName: config.app_name,
        baseUrl: config.base_url,
        deepLink: config.deep_link,
        category: config.category_name
      }
    };
  }

  private extractConfiguration(): ConfigurationData {
    const config = JSON.parse(
      this.extractAsset('assets/config/zalopay_config.json').toString('utf8')
    );

    return {
      phoneFormatPatterns: config.general.phone_format.patterns,
      apiTimeout: config.general.api_timeout,
      clickTimeout: config.general.click_timeout,
      version: '11.17.0',
      buildNumber: 16
    };
  }
}

// Usage example
async function main() {
  const analyzer = new APKAnalyzer('/home/user/-/zalopay_extracted/vn.com.vng.zalopay.apk');
  const result = analyzer.analyzeStructure();
  
  console.log('Analysis complete:', result.totalFiles, 'files analyzed');
  console.log('Assets found:', result.assets.length);
  console.log('ML Models:', result.mlModels.length);
  console.log('Tracking data extracted:', result.trackingData.piiData.name);
}

main().catch(console.error);