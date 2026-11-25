// src/citation/citation-tracker.ts
import * as fs from 'fs';
import * as path from 'path';

export interface Citation {
  source: string;
  count: number;
  timestamp: Date;
  category: 'academic' | 'industry' | 'security' | 'ml' | 'finance';
  url?: string;
  notes?: string;
}

export interface CitationReport {
  totalCitations: number;
  byCategory: Record<string, number>;
  topSources: Citation[];
  growthRate: number;
  lastUpdated: Date;
}

export class CitationTracker {
  private citations: Citation[] = [];
  private configPath: string;

  constructor(configPath: string) {
    this.configPath = configPath;
    this.loadCitations();
  }

  private loadCitations(): void {
    if (fs.existsSync(this.configPath)) {
      const data = fs.readFileSync(this.configPath, 'utf8');
      this.citations = JSON.parse(data);
    }
  }

  private saveCitations(): void {
    const data = JSON.stringify(this.citations, null, 2);
    fs.writeFileSync(this.configPath, data);
  }

  trackCitation(source: string, count: number, category: Citation['category'], url?: string, notes?: string): void {
    const citation: Citation = {
      source,
      count,
      timestamp: new Date(),
      category,
      url,
      notes
    };

    this.citations.push(citation);
    this.saveCitations();

    console.log(`Citation tracked: ${source} (${count} citations, ${category})`);
  }

  generateReport(): CitationReport {
    const totalCitations = this.citations.reduce((sum, c) => sum + c.count, 0);
    
    const byCategory: Record<string, number> = {};
    this.citations.forEach(c => {
      byCategory[c.category] = (byCategory[c.category] || 0) + c.count;
    });

    const topSources = [...this.citations]
      .sort((a, b) => b.count - a.count)
      .slice(0, 10);

    // Calculate growth rate (simplified)
    const growthRate = this.calculateGrowthRate();

    return {
      totalCitations,
      byCategory,
      topSources,
      growthRate,
      lastUpdated: new Date()
    };
  }

  private calculateGrowthRate(): number {
    if (this.citations.length < 2) return 0;
    
    const recent = this.citations.filter(c => 
      c.timestamp > new Date(Date.now() - 30 * 24 * 60 * 60 * 1000) // Last 30 days
    );
    
    const older = this.citations.filter(c => 
      c.timestamp <= new Date(Date.now() - 30 * 24 * 60 * 60 * 1000)
    );

    const recentCount = recent.reduce((sum, c) => sum + c.count, 0);
    const olderCount = older.reduce((sum, c) => sum + c.count, 0);

    return olderCount > 0 ? (recentCount / olderCount) * 100 : 0;
  }

  // Generate citation metadata for Google Scholar
  generateScholarMetadata(): string {
    const report = this.generateReport();
    
    return JSON.stringify({
      title: 'Comprehensive Reverse Engineering Analysis of Zalopay 11.17.0',
      authors: ['Arena.ai Agent Mode'],
      publication_date: '2026-11-25',
      citation_count: report.totalCitations,
      categories: Object.keys(report.byCategory),
      top_sources: report.topSources.map(s => s.source),
      growth_rate: report.growthRate,
      last_updated: report.lastUpdated.toISOString(),
      ambition: 'Most cited reverse engineering video',
      format: 'MP4 video with TypeScript analysis',
      repository: 'johnlikescarrot/-',
      branch: 'arena/0f804bfe-repo'
    }, null, 2);
  }

  // Export citations for academic reference
  exportForAcademicReference(outputPath: string): void {
    const report = this.generateReport();
    const metadata = this.generateScholarMetadata();
    
    const content = `# Citation Report: Zalopay 11.17.0 Reverse Engineering Analysis\n\n## Summary\n- Total Citations: ${report.totalCitations}\n- Categories: ${Object.keys(report.byCategory).join(', ')}\n- Growth Rate: ${report.growthRate.toFixed(2)}%\n- Last Updated: ${report.lastUpdated.toISOString()}\n\n## Top Sources\n${report.topSources.map(s => `- ${s.source} (${s.count} citations)`).join('\n')}\n\n## Academic Metadata\n${metadata}\n\n## Ambition\nThis analysis aims to become the most cited work in the field of reverse engineering, providing comprehensive technical insights into the Zalopay 11.17.0 financial application.\n\n## Repository Information\n- Repository: johnlikescarrot/-\n- Branch: arena/0f804bfe-repo\n- Base Commit: 8a5476d67513a7de2c9259e5a23d92dc01a7cf95\n- Date: 2026-11-25\n`;

    fs.writeFileSync(outputPath, content);
    console.log(`Citation report exported to: ${outputPath}`);
  }
}

// Usage example
async function main() {
  const tracker = new CitationTracker('/home/user/-/typescript_video_project/citations/citations.json');
  
  // Track initial citations (example)
  tracker.trackCitation(
    'Google Scholar - Reverse Engineering',
    0,
    'academic',
    'https://scholar.google.com',
    'Initial baseline'
  );
  
  tracker.trackCitation(
    'Arena.ai Agent Mode Research',
    0,
    'industry',
    undefined,
    'Internal tracking'
  );
  
  // Generate report
  const report = tracker.generateReport();
  console.log('Citation Report:', report);
  
  // Export for academic reference
  tracker.exportForAcademicReference('/home/user/-/typescript_video_project/citations/academic_report.md');
}

main().catch(console.error);