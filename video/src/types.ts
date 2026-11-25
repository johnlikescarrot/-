export interface MasterMetrics {
  study_metadata: {
    target_app: string;
    package_name: string;
    version_name: string;
    version_code: number;
    min_sdk: number;
    target_sdk: number;
    compile_sdk: number;
    source_archive: string;
    source_archive_sha256: string;
    xapk_sha256: string;
    rea_version: string;
  };
  container_scale: {
    outer_zip_bytes: number;
    xapk_bytes: number;
    split_apk_count: number;
    total_apk_entries: number;
    nested_container_files: number;
    total_analyzed_artifacts: number;
    unique_sha256_count: number;
    duplicate_groups: number;
    duplicate_wasted_bytes: number;
    total_uncompressed_bytes: number;
    total_compressed_bytes: number;
    split_apks: Array<{
      name: string;
      apk_bytes: number;
      entries: number;
      uncompressed_bytes: number;
      avg_entropy: number;
      signing_schemes: string[];
    }>;
    categories: Array<{
      category: string;
      count: number;
      uncompressed_bytes: number;
      share_pct: number;
    }>;
  };
  signing_integrity: {
    schemes: string[];
    v1_signer: Record<string, unknown>;
    rea_graph_sha256_base_apk: string;
    rea_integrity_contradictions: number;
  };
  manifest_surface: {
    permissions_total: number;
    permissions_by_category: Record<string, number>;
    components: Record<string, {
      total: number;
      exported_true: number;
      exported_false: number;
      permission_protected: number;
      custom_process_isolated: number;
    }>;
    isolated_processes: string[];
    deep_link_schemes: string[];
    allow_backup: string;
    application_entrypoint: string;
    network_security_configs: Array<{
      file: string;
      cleartext_flags: string[];
      domain_count: number;
      domains_sample: string[];
    }>;
  };
  dex_architecture: {
    total_dex_files: number;
    total_dex_bytes: number;
    aggregate: {
      total_class_defs: number;
      total_method_ids_sum: number;
      total_field_ids_sum: number;
      total_string_ids_sum: number;
      short_obfuscated_classes: number;
      short_obfuscated_ratio: number;
      kotlin_marker_classes: number;
      compose_related_classes: number;
    };
    sdk_distribution: Array<{
      ecosystem: string;
      classes: number;
      share_pct: number;
    }>;
    per_dex: Array<{
      filename: string;
      size_bytes: number;
      method_ids_count: number;
      class_defs_count: number;
      obfuscated_ratio: number;
    }>;
  };
  native_elf_hardening: {
    summary: {
      total_elf_libraries: number;
      total_bytes: number;
      pie_enabled_count: number;
      nx_enabled_count: number;
      full_relro_count: number;
      partial_relro_count: number;
      no_relro_count: number;
      stack_canary_count: number;
      fortify_source_count: number;
      stripped_symtab_count: number;
      jni_onload_count: number;
      dt_needed_frequency: Record<string, number>;
      category_byte_totals: Record<string, number>;
    };
    libraries: Array<{
      name: string;
      category: string;
      bytes: number;
      entropy: number;
      pie: boolean;
      nx: boolean;
      relro: string;
      stack_canary: boolean;
      fortify: boolean;
      stripped: boolean;
      jni_exports: number;
      dt_needed: string[];
      compiler: string | null;
    }>;
  };
  hybrid_and_ai_stack: {
    androidx_version_manifests: number;
    ml_total_artifacts: number;
    ml_total_bytes: number;
    ml_by_engine: Record<string, { count: number; bytes: number }>;
    react_native: {
      bundle_bytes: number;
      bundle_lines: number;
      __d_module_definitions_count: number;
      asset_feature_modules: Record<string, number>;
      asset_node_modules_packages: Record<string, number>;
    };
    griver_mini_programs: Record<string, {
      tar_file_count: number;
      tar_total_bytes: number;
    }>;
    miniapp_config_schema: {
      size_bytes: number;
      list_counts?: Record<string, number>;
    };
    shield_dat_payloads: Array<{ path: string; bytes: number }>;
  };
}

export interface SceneTiming {
  id: string;
  index: number;
  title: string;
  subtitle: string;
  narration: string;
  narrationVi: string;
  durationSec: number;
  audioPath?: string;
}
